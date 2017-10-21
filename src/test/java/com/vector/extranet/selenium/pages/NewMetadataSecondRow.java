package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class NewMetadataSecondRow extends BasePage {

    @FindBy(xpath = ".//*[@id='key%new-field1']")
    private WebElement keyInput;
    
    @FindBy(xpath = ".//*[@id='labelEnglish%new-field1']")
    private WebElement nameInput;
    
    @FindBy(xpath = ".//*[@id='countryCode%new-field1']")
    private WebElement typeDropdown;
    
    @FindBy(xpath = ".//vec-dl-metadata-admin-ui-list-item[2]//div[@class='vec-app-col'][6]/input")
    private WebElement showInMemberAreaCheckbox;
    
    @FindBy(xpath = ".//vec-dl-metadata-admin-ui-list-item[2]//div[@class='vec-app-col'][7]/input")
    private WebElement showInSharedFolderCheckbox;
    
    @FindBy(xpath = ".//vec-dl-metadata-admin-ui-list-item[2]//div[@class='vec-app-col']/div[contains(text(), 'Remove')]")
    private WebElement removeButton;
    
    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Save')]")
    private WebElement saveButton;
    
    public NewMetadataSecondRow keyInput(String key) {
	sendKeys(keyInput, key);
	return this;
    }

    public NewMetadataSecondRow nameInput(String name) {
	sendKeys(nameInput, name);
	return this;
    }

    /* 
     * "number"
     * or
     * "string"
     */
    public NewMetadataSecondRow type(String typeValue) {
	elementsUtil.selectValueFromDropdown(typeDropdown, typeValue);
	return this;
    }
    
    public NewMetadataSecondRow removeButton() {
	click(removeButton);
	return this;
    }
    
    public NewMetadataSecondRow showInMemberArea() {
	click(showInMemberAreaCheckbox);
	return this;
    }
    
    public NewMetadataSecondRow showInSharedFolder() {
	click(showInSharedFolderCheckbox);
	return this;
    }
    
    public NewMetadataSecondRow saveButton() {
	click(saveButton);
	return this;
    }
    
}