package com.vector.extranet.selenium.test.account;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.ModifyAccountPage;
import com.vector.extranet.selenium.pages.TabsMenu;
import com.vector.extranet.selenium.pages.TabsNavigation;

public class ModifyAccountTest extends BaseTest {

    @Test
    public void isModifyPageUserInfosCorrect() {
	UserAccount user = getUser();

	loginService.toLoginPage(user);

	TabsNavigation tabsNavigation = pages.getPageBy(TabsNavigation.class);
	tabsNavigation.myAccountPage();

	TabsMenu tabsMenu = pages.getPageBy(TabsMenu.class);
	tabsMenu.modifyAccount();
	ModifyAccountPage modifyAccountPage = pages.getPageBy(ModifyAccountPage.class);

	assertThat(modifyAccountPage.getEmail(), equalTo(user.getEmail()));
	assertThat(modifyAccountPage.getLastName(), equalTo(user.getLastName()));
	assertThat(modifyAccountPage.getFirstName(), equalTo(user.getFirstName()));
	assertThat(modifyAccountPage.getCountry(), equalTo(user.getCountry()));
	assertThat(modifyAccountPage.getPostcode(), equalTo(user.getPostCode()));
	assertThat(modifyAccountPage.getCity(), equalTo(user.getCity()));
	assertThat(modifyAccountPage.getTelefonNumber(), equalTo(user.getTelefonNumber()));
	assertThat(modifyAccountPage.getFaxNumber(), equalTo(user.getFaxNumber()));
	assertThat(modifyAccountPage.getEmail(), equalTo(user.getEmail()));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return accountService.createNewUserAndActivate();
    }

    /*   !FORGET
     * @Test public void canModifySuccessfullyExceptEmail() { ModifyAccountPage
     * modifyAccountPage = Pages.getPageBy(ModifyAccountPage.class);
     * 
     * modifyAccountPage. }
     */

}
