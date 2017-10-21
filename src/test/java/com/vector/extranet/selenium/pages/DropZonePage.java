package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class DropZonePage extends BasePage {

    @FindBy(xpath = ".//input[@type='file']")
    private WebElement uploadFile;

    @FindBy(xpath = ".//div[@class='email-input']//input")
    private WebElement emailInput;

    @FindBy(id = "shareButton")
    private WebElement uploadButton;
    
    @FindBy(xpath = ".//div[@id='termsOfUseDiv']//input[2]")
    private WebElement termsOfUse;

    public DropZonePage uploadFile(String filePath) {
	elementsUtil.makeVisibleElementDropZone();
	uploadFile.sendKeys(filePath);
	elementsUtil.waitUntilElementVisibleBy(".//*[@id='fileList']//*[contains(text(),'Ready for upload')]");
	return this;
    }
    
    public DropZonePage emailInput(String email) {
	emailInput.sendKeys(email);
	return this;
    }
    
    public DropZonePage uploadButton() {
	click(uploadButton);
	return this;
    }
    
    public DropZonePage termsOfUse() {
	if(termsOfUse != null) {
	    click(termsOfUse);
	}
	
	return this;
    }
}
