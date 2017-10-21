package com.vector.extranet.selenium.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class AddMemberPage extends BasePage {

    @FindBy(xpath = "//div[@class='vec-ma-ua-toolbar']/button")
    private WebElement addMember;
    
    @FindBy(xpath = "")
    private List<WebElement> members;
    
    public AddMemberPage addMember() {
	click(addMember);
	return this;
    }
    
}
