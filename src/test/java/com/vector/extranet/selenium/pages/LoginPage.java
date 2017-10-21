package com.vector.extranet.selenium.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.PageWithTitle;
import com.vector.extranet.selenium.model.UserAccount;

public class LoginPage extends PageWithTitle {

    private final static String PORTLET_TITLE = "Sign In";
    private final static String PAGE_TITLE = "Login";

    @FindBy(xpath = "//input[@name = 'j_username' or @name = 'login']")
    private WebElement email;

    @FindBy(xpath = "//input[@name = 'j_password' or @name = 'password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//a[contains(text(), 'Create Account')]")
    private WebElement createAccount;

    @FindBy(xpath = "//a[contains(@href, 'resetpassword')]")
    private WebElement resetPassword;

    @FindBy(xpath = "//h1[@class='portlet-title']//span[@class='portlet-title-text']/text()")
    private WebElement pageTitle;

    @Override
    public String getPortletTitle() {
	return PORTLET_TITLE;
    }

    @Override
    public String getPageTitle() {
	return PAGE_TITLE;
    }

    public void clickCreateAccount() {
	createAccount.click();
    }

    public void clickResetPassword() {
	click(resetPassword);
    }

    public void loginAs(String email, String password) {
	sendKeys(this.email, email);
	sendKeys(this.password, password);
	click(submit);
    }

    public void loginAs(UserAccount user) {
	loginAs(user.getEmail(), user.getPassword());
    }

    public boolean isPageCorrect() {
	try {
	    return StringUtils.equals(pageTitle.getText(), PAGE_TITLE);
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

}
