package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class NewMetadataFirstRow extends BasePage {

    @FindBy(xpath = ".//*[@id='key%new-field0']")
    private WebElement keyInput;
    
    @FindBy(xpath = ".//*[@id='labelEnglish%new-field0']")
    private WebElement nameInput;
    
    @FindBy(xpath = ".//*[@id='countryCode%new-field0']")
    private WebElement typeDropdown;
    
    @FindBy(xpath = ".//*[@class='vec-app-col'][6]/input")
    private WebElement showInMemberAreaCheckbox;
    
    @FindBy(xpath = ".//*[@class='vec-app-col'][7]/input")
    private WebElement showInSharedFolderCheckbox;
    
    @FindBy(xpath = ".//*[@class='vec-app-col']/div[contains(text(), 'Remove')]")
    private WebElement removeButton;
    
    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Save')]")
    private WebElement saveButton;
    
    public NewMetadataFirstRow keyInput(String key) {
	sendKeys(keyInput, key);
	return this;
    }

    public NewMetadataFirstRow nameInput(String name) {
	sendKeys(nameInput, name);
	return this;
    }

    /* 
     * "number"
     * or
     * "string"
     */
    public NewMetadataFirstRow type(String typeValue) {
	elementsUtil.selectValueFromDropdown(typeDropdown, typeValue);
	return this;
    }
    
    public NewMetadataFirstRow removeButton() {
	click(removeButton);
	return this;
    }
    
    public NewMetadataFirstRow showInMemberArea() {
	click(showInMemberAreaCheckbox);
	return this;
    }
    
    public NewMetadataFirstRow showInSharedFolder() {
	click(showInSharedFolderCheckbox);
	return this;
    }
    
    public NewMetadataFirstRow saveButton() {
	click(saveButton);
	return this;
    }
    
}
