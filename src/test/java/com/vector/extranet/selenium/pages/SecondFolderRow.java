package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class SecondFolderRow extends BasePage {
    @FindBy(xpath = ".//vec-dl-ui-entry-list-item[2]//div[@class='vec-dl-ui-select-item']/input")
    private WebElement checkbox;
    
    @FindBy(xpath = ".//vec-dl-ui-entry-list-item[2]//div[@class='vec-dl-ui-name']/a")
    private WebElement folderName;
    
    @FindBy(xpath = ".//vec-dl-ui-entry-list-item[2]//div[@class='vec-dl-ui-shared vec-dl-ui-show']")
    private WebElement sharedFolderIcon;
    
    @FindBy(xpath = ".//vec-dl-ui-entry-list-item[2]//div[@class='vec-dl-ui-open-context-menu vec-dl-ui-show']")
    private WebElement moreOptionsIcon;
    
    public SecondFolderRow clickCheckbox() {
	click(checkbox);
	return this;
    }
    
    public SecondFolderRow clickFolderName() {
	click(folderName);
	return this;
    }
    
    public SecondFolderRow clickSharedFolderIcon() {
	click(sharedFolderIcon);
	return this;
    }
    
    public SecondFolderRow clickMoreOptionsIcon() {
	click(moreOptionsIcon);
	return this;
    }
    
    public String getFolderName() {
 	return folderName.getText().trim();
     }
}
