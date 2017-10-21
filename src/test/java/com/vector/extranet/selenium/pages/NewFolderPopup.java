package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class NewFolderPopup extends BasePage {
    
    @FindBy(id = "name")
    private WebElement folderNameInput;
    
    @FindBy(id = "comment")
    private WebElement commentArea;
    
    @FindBy(xpath = ".//div[@class='vec-app-buttons']//*[contains(text(),'Create')]")
    private WebElement createButton;
    
    @FindBy(xpath = ".//div[@class='vec-app-buttons']//*[contains(text(),'Cancel')]")
    private WebElement cancelButton;
    
    public NewFolderPopup folderNameInput(String folderName) {
	sendKeys(folderNameInput, folderName);
	return this;
    }
    
    public NewFolderPopup commentArea(String comment) {
	sendKeys(commentArea, comment);
	return this;
    }
    
    public NewFolderPopup createButton() {
	click(createButton);
	return this;
    }
    
    public NewFolderPopup cancelButton() {
	click(cancelButton);
	return this;
    }
}
