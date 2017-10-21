package com.vector.extranet.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vector.extranet.selenium.service.WebDriverService;

@Singleton
public class ElementsUtil {

    @Inject
    private WaitUtils waitUtils;

    @Inject
    private WebDriverService webDriverService;

    public void sendKeys(WebElement element, String value) {
	element.clear();
	element.sendKeys(value);
    }

    public void click(WebElement element) {
	element.click();
	waitUtils.waitMiliSecond(700); // !FORGET
    }

    public void makeVisibleElement(WebElement element, String name) {
	String js = String.format("document.getElementsByName('%s')[0].setAttribute('style', '');", name);

	((JavascriptExecutor) webDriverService.getWebDriver()).executeScript(js, element);
    }

    public void makeVisibleElementDropZone() {
	String js = "document.getElementById('dragandrophandler').setAttribute('id', '');";

	((JavascriptExecutor) webDriverService.getWebDriver()).executeScript(js);
    }

    public void selectValueFromDropdown(WebElement element, String value) {
	Select dropdown = new Select(element);
	dropdown.selectByValue(value);
    }

    public WebElement getSelectedOptionFromDropdown(WebElement element) {
	Select dropdown = new Select(element);
	return dropdown.getFirstSelectedOption();
    }

    public boolean isElementExist(WebElement element) {
	try {
	    element.getText();
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    public String getInputValue(WebElement element) {
	return element.getAttribute("value");
    }

    public void waitUntilElementClickable(WebElement element) {
	waitUtils.waitUntilElementClickable(element);
    }

    public void waitUntilPresenceOfElementLocatedBy(String id) {
	waitUtils.waitUntilPresenceOfElementLocatedBy(id);
    }

    public void waitUntilInvisibilityOfElementBy(String xpath) {
	waitUtils.waitUntilInvisibilityOfElementBy(xpath);
    }

    public void waitUntilClickableByXpath(String xpath) {
	waitUtils.waitUntilClickable(xpath);
    }
    
    public void waitUntilElementVisibleBy(String xpath) {
	waitUtils.waitUntil(xpath);
    }
    
    public boolean isElementExistByXpath(String xpath) {
	try {
	    webDriverService.getWebDriver().findElement(By.xpath(xpath));
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }
}
