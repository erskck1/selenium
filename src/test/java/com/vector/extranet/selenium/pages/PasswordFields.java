package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class PasswordFields extends BasePage {

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "passwordRepeat")
    private WebElement passwordRepeat;

    @FindBy(xpath = "// button[@type = 'submit']")
    private WebElement submit;

    public PasswordFields password(String password) {
	sendKeys(this.password, password);
	return this;
    }

    public PasswordFields passwordRepeat(String repeatPassword) {
	sendKeys(this.passwordRepeat, repeatPassword);
	return this;
    }

    public void save() {
	click(submit);
    }
}
