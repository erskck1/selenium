package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class MemberAreaTabs extends BasePage {
    
    @FindBy(xpath = ".//*[@id='main-menu']//a[contains(text(), 'Files')]")
    private WebElement filesTab;
    
    @FindBy(xpath = ".//*[@id='main-menu']//a[contains(text(), 'Admin Light')]")
    private WebElement adminLightTab;
    
    @FindBy(xpath = ".//*[@id='main-menu']//a[contains(text(), 'Metadata Administration')]")
    private WebElement metadataTab;
    
    public MemberAreaTabs filesTab() {
	click(filesTab);
	return this;
    }
    
    public MemberAreaTabs adminLightTab() {
	click(adminLightTab);
	return this;
    }
    
    public MemberAreaTabs metadataTab() {
	click(metadataTab);
	return this;
    }
    
}
