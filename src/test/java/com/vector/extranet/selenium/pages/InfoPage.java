package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class InfoPage extends BasePage {

    @FindBy(xpath = "//header[@class='portlet-topper']//span[@class='portlet-title-text']")
    private WebElement title;

    @FindBy(xpath = "//div[@class='portlet-body']/div[@class='alert alert-success']")
    private WebElement alertSuccess;

    public String getTitle() {
	return title.getText();
    }

    public String getAlertSuccessText() {
	return alertSuccess.getText();
    }
}
