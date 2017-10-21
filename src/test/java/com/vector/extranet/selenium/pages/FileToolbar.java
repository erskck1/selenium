package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class FileToolbar extends BasePage {

    @FindBy(xpath = ".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'Up')][1]")
    private WebElement upButton;
    
    @FindBy(xpath = ".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'New Folder')]")
    private WebElement newFolderButton;
    
    @FindBy(xpath = ".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'File Upload')]")
    private WebElement fileUploadButton;
    
    @FindBy(xpath = ".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'Move selected')]")
    private WebElement moveButton;
    
    @FindBy(xpath = ".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'Delete selected')]")
    private WebElement deleteButton;
    
    @FindBy(xpath = ".//input[@name='file']")
    private WebElement uploadFileInput;

    @FindBy(xpath = ".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'Folder Sizes')]")
    private WebElement folderSizes;
    
    public FileToolbar up() {
	click(upButton);
	elementsUtil.waitUntilInvisibilityOfElementBy(".//*[@class='vec-dl-ui-progress-bar ng-scope']");
	return this;
    }
    
    public FileToolbar newFolder() {
	click(newFolderButton);
	return this;
    }
    
    public FileToolbar fileUpload() {
	click(fileUploadButton);
	return this;
    }
    
    public FileToolbar move() {
	click(moveButton);
	return this;
    }
    
    public FileToolbar delete() {
	click(deleteButton);
	return this;
    }
    
    public FileToolbar uploadFile(String filePath) {
	elementsUtil.makeVisibleElement(uploadFileInput, "file");
	uploadFileInput.sendKeys(filePath);
	if(elementsUtil.isElementExistByXpath(".//div[@class='vec-dl-ui-file-name ng-binding']")) {
	    elementsUtil.waitUntilInvisibilityOfElementBy(".//div[@class='vec-dl-ui-file-name ng-binding']");
	}
	return this;
    }
    
    public FileToolbar folderSizes() {
	click(folderSizes);
	return this;
    }
}
