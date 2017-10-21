package com.vector.extranet.selenium.util;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vector.extranet.selenium.service.WebDriverService;

@Singleton
public class WaitUtils {

    @Inject
    private WebDriverService webDriverService;

    public void waitForLoad() {
	WebDriver driver = webDriverService.getWebDriver();
	Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
	    @Override
	    public Boolean apply(WebDriver driver) {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	    }
	};
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(function);
    }

    public void waitUntil(String xpath) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 10);

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilInvisibilityOfElementBy(String xpath) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 20);

	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }
    
    public void waitUntilInvisibilityOfElementBy(String xpath, int second) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), second);

	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilClickable(String xpath) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 10);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void waitUntilElementClickable(WebElement element) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 10);

	wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilPresenceOfElementLocatedBy(String id) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 10);

	wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public void waitUntilPresenceOfElementLocatedByXpath(String xpath) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 10);

	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilTextToBePresentInElement(String xPath, String text) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 10);

	wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xPath), text));
    }

    public void waitUntilTextToBePresentInElement(String xPath, String text, int sec) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), sec);

	wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xPath), text));
    }
    
    public void waitOneSecond() {
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
    
    public void waitMiliSecond(int milisecond) {
	try {
	    Thread.sleep(milisecond);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void waitSeconds(int second) {
	try {
	    Thread.sleep(second * 1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void waituntilElementVisible(WebElement element) {
	WebDriverWait wait = new WebDriverWait(webDriverService.getWebDriver(), 10);

	wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilNewTabOpen() {
	boolean check = false;
	int count = 0;
	while (!check) {
	    try {
		Set<String> winHandle = webDriverService.getWebDriver().getWindowHandles();
		if (winHandle.size() > 1) {
		    check = true;
		    break;
		}
		Thread.sleep(1000);
		count++;
		if (count > 10) {
		    break;
		}
	    } catch (Exception e) {
	    }
	}
    }
}
