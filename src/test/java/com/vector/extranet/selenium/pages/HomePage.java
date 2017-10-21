package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(id = "vector-sign-in")
    private WebElement signInButton;

    @FindBy(xpath = "//a[contains(text(), 'Create Account')]")
    private WebElement createAccountLink;

}
