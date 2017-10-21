package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.PageWithTitle;

public class ResetPasswordRequestPage extends PageWithTitle {

    private final static String PORTLET_TITLE = "Reset password";
    private final static String PAGE_TITLE = "Reset Password";

    @FindBy(xpath = "//*[@id='reset-password-email']")
    private WebElement email;

    @FindBy(xpath = "//*[@class='vectorResetPassword']//div[@class='form-submit-button']/button")
    private WebElement submit;

    @FindBy(xpath = "//div[@id='vector-login-actions']//a[contains(text(), 'Sign In')]")
    private WebElement signIn;

    @FindBy(xpath = "//div[@id='vector-login-actions']//a[contains(text(), 'Create Account')]")
    private WebElement createAccount;

    public void submit() {
	click(submit);
    }

    public ResetPasswordRequestPage email(String email) {
	sendKeys(this.email, email);
	return this;
    }

    public void resetPassword(String email) {
	email(email).submit();
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
