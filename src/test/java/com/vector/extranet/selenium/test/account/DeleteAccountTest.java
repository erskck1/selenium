package com.vector.extranet.selenium.test.account;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.framework.FailTestListener;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.CreateAccountPage;
import com.vector.extranet.selenium.pages.DeleteAccountPage;
import com.vector.extranet.selenium.pages.InfoPage;
import com.vector.extranet.selenium.pages.TabsMenu;
import com.vector.extranet.selenium.pages.TabsNavigation;

@Listeners(FailTestListener.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeleteAccountTest extends BaseTest {
    
    @Test
    public void _1_isCorrectPage(ITestContext context) {
	pages.TopNavigation().login();
	loginAs(getUser());

	TabsNavigation tabsNavigation = pages.getPageBy(TabsNavigation.class);

	tabsNavigation.myAccountPage();

	TabsMenu tabsMenu = pages.getPageBy(TabsMenu.class);

	tabsMenu.deleteAccount();

	DeleteAccountPage deleteAccountPage = pages.getPageBy(DeleteAccountPage.class);

	assertThat(deleteAccountPage.getPageTitle(), equalTo("Delete Account"));
	assertThat(deleteAccountPage.getPortletTitle(), equalTo("Delete Account"));
	assertTrue(testUtils.containsText("Are you sure you want to delete your account?"));
    }

    @Test
    public void _2_canDeleteAccount() {
	DeleteAccountPage deleteAccountPage = pages.getPageBy(DeleteAccountPage.class);

	deleteAccountPage.delete();

	assertTrue(testUtils.containsText("Welcome to the Vector Customer Portal"));
	assertTrue(pages.TopNavigation().isLoginButtonVisible());

	getUser().setActivated(false);
    }

    @Test
    public void _3_canNotLoginWithDeletedAccount() {
	pages.TopNavigation().login();

	loginAs(getUser());

	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.requestFailed")));
	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.authenticationFailed")));
    }

    @Test
    public void _4_canAgainRegisterWithDeletedAccount() {
	pages.Login().clickCreateAccount();

	CreateAccountPage createAccountPage = pages.getPageBy(CreateAccountPage.class);

	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	createAccountPage.fillInputFieldBy(getUser()).save();

	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	String tokenForActivation = activationEmailLink.getTokenUrlForAccountActivation(getUser().getEmail());
	testUtils.getPageByUrl(tokenForActivation);

	assertThat(testUtils.getPortletTextByClass("vectorUserManagement"), equalTo("The activation of your account was successful."));
	assertThat(pages.getPageBy(InfoPage.class).getTitle(), equalTo("Activation"));

	getUser().setActivated(true);
    }

    @Test
    public void _5_canLoginWithRecreatedAccount() {
	pages.TopNavigation().login();

	loginAs(getUser());

	assertTrue(pages.TopNavigation().isLoginSuccess());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return accountService.createNewUserAndActivate();
    }
}
