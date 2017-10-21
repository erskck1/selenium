package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.PageWithTitle;

public class ResetPasswordPage extends PageWithTitle {

    private final static String PORTLET_TITLE = "New Password";
    private final static String PAGE_TITLE = "New Password";

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "passwordRepeat")
    private WebElement passwordRepeat;

    @FindBy(xpath = "// button[@type = 'submit']")
    private WebElement submit;

    public ResetPasswordPage password(String password) {
	sendKeys(this.password, password);
	return this;
    }

    public ResetPasswordPage passwordRepeat(String passwordRepeat) {
	sendKeys(this.passwordRepeat, passwordRepeat);
	return this;
    }

    public void submit() {
	click(submit);
    }

    @Override
    public String getPageTitle() {
	return PAGE_TITLE;
    }

    @Override
    public String getPortletTitle() {
	return PORTLET_TITLE;
    }

}
