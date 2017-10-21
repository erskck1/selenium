package com.vector.extranet.selenium.test.common;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.TabsMenu;
import com.vector.extranet.selenium.pages.TabsNavigation;

public class MyAccountTabMenuTest extends BaseTest {

    @Test(description = "check the actions of the my account tab existivity")
    public void isMenuElementsExist() {
	loginService.toLoginPage(getUser());
	
	pages.getPageBy(TabsNavigation.class).myAccountPage();

	TabsMenu tabsMenu = pages.getPageBy(TabsMenu.class);

	assertTrue(tabsMenu.isChangePasswordExist());
	assertTrue(tabsMenu.isChangePasswordTextCorrect());
	assertTrue(tabsMenu.isDeleteAccountExist());
	assertTrue(tabsMenu.isDeleteAccountTextCorrect());
	assertTrue(tabsMenu.isModifyAccountExist());
	assertTrue(tabsMenu.isModifyAccountTextCorrect());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getActiveUser();
    }
}
