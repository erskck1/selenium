package com.vector.extranet.selenium.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class TopNavigationPage extends BasePage {

    @FindBy(xpath = "//div[@id='language-menu']/button[@id='dropdownMenu']")
    private WebElement languageButton;

    @FindBy(id = "vector-sign-in")
    private WebElement login;

    @FindBy(id = "//div[@id='heading']/h1/a")
    private WebElement logoLink;

    @FindBy(xpath = "//*[@id='search-box']/input[4]")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@id='search-box']/input[5]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='dropdownMenuAccount']")
    private WebElement myAccountButton;

    @FindBy(xpath = "//*[@id='account-menu']/ul//a[@href='/c/portal/logout']")
    private WebElement logout;

    @FindBy(xpath = "//*[@id='account-menu']/ul/li[1]/a")
    private WebElement myAccount;

    @FindBy(xpath = "//*[@id='langSelection']//a")
    private List<WebElement> languages;

    public void login() {
	click(login);
    }

    public boolean isLoginButtonVisible() {
	return login.isDisplayed();
    }

    public void myAccountButton() {
	click(myAccountButton);
    }

    public void logOut() {
	click(logout);
    }

    public boolean isLoginSuccessAs(String nameSurname) {
	try {
	    return StringUtils.equals(nameSurname, myAccountButton.getText());
	} catch (Exception e) {
	    return false;
	}
    }

    public boolean isLoginSuccess() {
	return elementsUtil.isElementExist(myAccountButton);
    }
}
