package com.vector.extranet.selenium.base;

import org.openqa.selenium.WebElement;

import com.vector.extranet.selenium.util.ElementsUtil;

public abstract class BasePage {

    protected ElementsUtil elementsUtil;

    public void setElementsUtil(ElementsUtil elementsUtil) {
	this.elementsUtil = elementsUtil;
    }

    protected void sendKeys(WebElement element, String value) {
	elementsUtil.sendKeys(element, value);
    }

    protected void click(WebElement element) {
	elementsUtil.click(element);
    }

}
