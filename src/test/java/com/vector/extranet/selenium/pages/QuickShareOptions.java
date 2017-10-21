package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class QuickShareOptions extends BasePage {

    @FindBy(xpath = ".//*[@class='ng-scope']//*[contains(text(),'Properties')]")
    private WebElement properties;
    
    @FindBy(xpath = ".//*[@class='ng-scope']//*[contains(text(),'Modify Share')]")
    private WebElement modifyShare;
    
    @FindBy(xpath = ".//*[@class='ng-scope']//*[contains(text(),'Share')]")
    private WebElement share;
    
    @FindBy(xpath = ".//*[@class='ng-scope']//*[contains(text(),'Create Drop Zone')]")
    private WebElement createDropZone;
    
    @FindBy(xpath = ".//*[@class='ng-scope']//*[contains(text(),'Delete')]")
    private WebElement delete;
    
    @FindBy(xpath = ".//*[@class='ng-scope']//*[contains(text(),'Download as ZIP')]")
    private WebElement downloadAsZip;
    
    public QuickShareOptions clickProperties() {
	click(properties);
	return this;
    }
    
    public QuickShareOptions clickModifyShare() {
	click(modifyShare);
	return this;
    }
    
    public QuickShareOptions clickCreateDropZone() {
	click(createDropZone);
	return this;
    }
    
    public QuickShareOptions clickDelete() {
	click(delete);
	return this;
    }
    
    public QuickShareOptions clickDownloadAsZip() {
	click(downloadAsZip);
	return this;
    }
    
    public QuickShareOptions clickShare() {
	click(share);
	return this;
    }
}
