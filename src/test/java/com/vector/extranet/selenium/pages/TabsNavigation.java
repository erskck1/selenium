package com.vector.extranet.selenium.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class TabsNavigation extends BasePage {

    @FindBy(xpath = "//ul[@id='main-menu']//a[@href='/web/my-account']")
    private WebElement myAccountTab;

    @FindBy(xpath = "//ul[@id='main-menu']//a[@href='/web/admin-light']")
    private WebElement memberAreaAdministration;

    public boolean isMyAccountTabExist() {
	return elementsUtil.isElementExist(myAccountTab);
    }

    public boolean isMyAccountTabTextCorrect() {
	return StringUtils.equals(myAccountTab.getText().trim(), "My Account");
    }

    public boolean isMemberAreaAdministrationTabExist() {
	return elementsUtil.isElementExist(memberAreaAdministration);
    }

    public void myAccountPage() {
	click(myAccountTab);
    }

    public void memberAreaAdministration() {
	click(memberAreaAdministration);
    }
}
