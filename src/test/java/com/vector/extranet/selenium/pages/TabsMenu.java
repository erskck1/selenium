package com.vector.extranet.selenium.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class TabsMenu extends BasePage {

    @FindBy(xpath = "//li/ul[@class='child-menu']/li")
    private List<WebElement> menuOptions;

    public WebElement getWebElementBy(int index) {
	return menuOptions.get(index);
    }

    public boolean isModifyAccountExist() {
	return elementsUtil.isElementExist(menuOptions.get(0));
    }

    public boolean isChangePasswordExist() {
	return elementsUtil.isElementExist(menuOptions.get(1));
    }

    public boolean isDeleteAccountExist() {
	return elementsUtil.isElementExist(menuOptions.get(2));
    }

    public boolean isModifyAccountTextCorrect() {
	return StringUtils.equals(menuOptions.get(0).getText(), "Modify Account");
    }

    public boolean isChangePasswordTextCorrect() {
	return StringUtils.equals(menuOptions.get(1).getText(), "Change Password");
    }

    public boolean isDeleteAccountTextCorrect() {
	return StringUtils.equals(menuOptions.get(2).getText(), "Delete Account");
    }

    public void modifyAccount() {
	click(menuOptions.get(0));
    }

    public void deleteAccount() {
	click(menuOptions.get(2));
    }

}
