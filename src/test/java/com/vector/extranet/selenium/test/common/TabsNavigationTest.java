package com.vector.extranet.selenium.test.common;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.TabsNavigation;

public class TabsNavigationTest extends BaseTest {

    @Test(description = "Check, if actions of my account tab existivity")
    public void isTabsExist() {
	loginService.toLoginPage(getUser());
	
	TabsNavigation tabsNavigation = pages.getPageBy(TabsNavigation.class);

	assertTrue(tabsNavigation.isMyAccountTabExist());
	assertTrue(tabsNavigation.isMyAccountTabTextCorrect());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getActiveUser();
    }

}
