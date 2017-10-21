package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class MemberAreaAdministrationPage extends BasePage {

    @FindBy(xpath=".//div/a[@class='new-member-area']")
    private WebElement createNewMemberArea;
    
    public void createNewMemberArea() {
	click(createNewMemberArea);
    }
    
}
