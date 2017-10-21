package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.model.UserAccount;

public class CreateAccountPage extends AccountPage {

    private final static String PORTLET_TITLE = "Self Registration";
    private final static String PAGE_TITLE = "Self Registration";

    @FindBy(id = "password")
    protected WebElement password;

    @FindBy(id = "passwordRepeat")
    protected WebElement passwordRepeat;

    @Override
    public String getPortletTitle() {
	return PORTLET_TITLE;
    }

    @Override
    public String getPageTitle() {
	return PAGE_TITLE;
    }

    public CreateAccountPage password(String password) {
	sendKeys(this.password, password);
	return this;
    }

    public CreateAccountPage passwordRepeat(String passwordRepeat) {
	sendKeys(this.passwordRepeat, passwordRepeat);
	return this;
    }

    @Override
    public CreateAccountPage fillInputFieldBy(UserAccount user) {
	sendKeys(this.password, user.getPassword());
	sendKeys(this.passwordRepeat, user.getPasswordRepeat());
	super.fillInputFieldBy(user);
	return this;
    }

}
