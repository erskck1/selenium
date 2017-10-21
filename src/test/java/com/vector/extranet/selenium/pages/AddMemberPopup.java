package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class AddMemberPopup extends BasePage {

    @FindBy(xpath="//*[@class='vec-ma-ua-search-candidates-form']/input")
    private WebElement searchBox;
    
    @FindBy(xpath=".//*[@class='vec-ma-ua-search-candidates-form']/button")
    private WebElement searchButton;
    
    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[@class='ng-binding'][1]")
    private WebElement addSelectedUsers;
    
    @FindBy(id = "role")
    private WebElement roleDropdown;
    
    public AddMemberPopup searchButton() {
	click(searchButton);
	return this;
    }
    
    public AddMemberPopup addSelectedUsers() {
	click(addSelectedUsers);
	return this;
    }
    
    public AddMemberPopup searchBox(String searchTerm) {
	sendKeys(searchBox, searchTerm);
	return this;
    }
    
    /*
     * Role Definitions : 
     * MemberArea User
     * MemberArea Manager
     * MemberArea PowerUser
     * MemberArea Project Member
     * MemberArea Uploader
     * MemberArea UploaderAnywhere
     * Site Administrator
     * Site Member
     */
    public AddMemberPopup roleDropdown(String roleDefinition) {
	elementsUtil.selectValueFromDropdown(roleDropdown, roleDefinition);
	return this;
    }
}
