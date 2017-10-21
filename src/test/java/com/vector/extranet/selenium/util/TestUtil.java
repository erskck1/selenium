package com.vector.extranet.selenium.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vector.extranet.selenium.framework.Configuration;
import com.vector.extranet.selenium.service.WebDriverService;

@Singleton
public class TestUtil {
    private static final String USER_NAME_PREFIX = "Selenium_User";
    private static final String TEMPLATE_EMAIL = "_Mail@vector.com";

    @Inject
    private WebDriverService webDriverService;

    @Inject
    private Configuration configuration;

    @Inject
    private WaitUtils waitUtils;

    public static String createRandomEmail() {
	return String.format("%s%s%s", USER_NAME_PREFIX, new SimpleDateFormat("yyMMddHHmmss").format(new Date()), TEMPLATE_EMAIL);
    }

    public boolean containsText(String text) {
	String xpath = String.format("//*[contains(text(),'%s')]", text);
	List<WebElement> list = webDriverService.getWebDriver().findElements(By.xpath(xpath));
	return list.size() > 0;
    }

    public boolean containsText(String xpath, String text) {
	String formattedXpath = String.format(xpath, text);
	List<WebElement> list = webDriverService.getWebDriver().findElements(By.xpath(formattedXpath));
	return list.size() > 0;
    }

    public boolean containsTextUnder(String xpath, String text) {
	String formattedXpath = String.format("%s//*[contains(text(),'%s')]", xpath, text);
	List<WebElement> list = webDriverService.getWebDriver().findElements(By.xpath(formattedXpath));
	return list.size() > 0;
    }

    public boolean isElementExistWithText(String xpath, String text) {
	WebElement element = webDriverService.getWebDriver().findElement(By.xpath(xpath));

	String elementText;
	try {
	    elementText = element.getText();
	} catch (Exception e) {
	    return false;
	}
	return StringUtils.equals(elementText.trim(), text.trim());
    }

    public boolean isElementExist(String id) {
	try {
	    webDriverService.getWebDriver().findElement(By.id(id));
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public boolean isElementExistByXpath(String xpath) {
	try {
	    webDriverService.getWebDriver().findElement(By.xpath(xpath));
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public boolean existsError(String id) {
	try {
	    webDriverService.getWebDriver().findElement(
		    By.xpath(String.format("//*[contains(concat(' ', normalize-space(@class), ' '), 'error') and @id='%s']", id)));
	} catch (NoSuchElementException e) {
	    return false;
	}
	return true;
    }

    public String getPortletTextByClass(String clss) {
	try {
	    return webDriverService.getWebDriver()
		    .findElement(By.xpath(String.format("//div[@class='portlet-body']/div[@class='%s']//p", clss))).getText();
	} catch (NoSuchElementException e) {
	    return "";
	}
    }

    public WebElement findElementBy(String xPath) {
	return webDriverService.getWebDriver().findElement(By.xpath(xPath));
    }

    public WebElement findElementById(String id) {
	return webDriverService.getWebDriver().findElement(By.id(id));
    }
    
    public List<WebElement> findElementsBy(String xpath) {
	return webDriverService.getWebDriver().findElements(By.xpath(xpath));
    }

    public void getPageBy(String key) {
	webDriverService.getWebDriver().get(configuration.getUrlOf(key));
	waitUtils.waitForLoad();
    }

    public void getPageByUrl(String url) {
	webDriverService.getWebDriver().get(url);
	waitUtils.waitForLoad();
    }

    public void homePage() {
	webDriverService.getWebDriver().get(configuration.landingPageUrl());
	waitUtils.waitForLoad();
    }

    public void directLogOut() {
	Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
	    
	    @Override
	    public Boolean apply(WebDriver webDriver) {
		logout(webDriver);

		if (StringUtils.equals(getPageTitle(), "Login - Customer Portal")) {
		    return Boolean.TRUE;
		}
		return Boolean.FALSE;
	    }

	    public void logout(WebDriver webDriver) {
		webDriver.get(configuration.landingPageUrl() + "/c/portal/logout");
		waitUtils.waitForLoad();
		webDriver.manage().deleteAllCookies();
		webDriver.get(configuration.landingPageUrl() + "/idp/Authn/UserPassword");
		waitUtils.waitForLoad();
	    }

	};

	try {
	    WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 30);
	    wait.until(function);
	} catch (Exception e) {
	    webDriverService.configureWebDriver("Previous Test continue...");
	}

    }

    public void newTab() {
	WebDriver webDriver = webDriverService.getWebDriver();
	String oldTab = webDriver.getWindowHandle();

	webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t"); // new
										    // tab
	waitUtils.waitUntilNewTabOpen();
	List<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
	tabs.remove(oldTab);
	webDriver.close(); // close old tab

	webDriver.switchTo().window(tabs.get(0));
    }

    public void defaultConfigurations() {
	directLogOut();
	homePage();
    }

    public List<WebElement> findAllElementsBy(String xpath) {
	return webDriverService.getWebDriver().findElements(By.xpath(xpath));
    }

    public void setFileDetector() {
	RemoteWebDriver remoteWebDriver = (RemoteWebDriver) webDriverService.getWebDriver();
	remoteWebDriver.setFileDetector(new LocalFileDetector());
    }

    public boolean isPageLoaded() {
	return ((JavascriptExecutor) webDriverService.getWebDriver()).executeScript("return document.readyState").equals("complete");
    }

    public String getCurrentUrl() {
	return webDriverService.getWebDriver().getCurrentUrl();
    }

    public String getPageTitle() {
	return webDriverService.getWebDriver().getTitle();
    }

    public void back() {
	webDriverService.getWebDriver().navigate().back();
	waitUtils.waitForLoad();
    }
}
