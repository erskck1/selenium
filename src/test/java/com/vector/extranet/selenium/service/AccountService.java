package com.vector.extranet.selenium.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vector.extranet.selenium.base.BaseService;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.DeleteAccountPage;
import com.vector.extranet.selenium.pages.InfoPage;
import com.vector.extranet.selenium.pages.TabsMenu;
import com.vector.extranet.selenium.pages.TabsNavigation;

@Singleton
public class AccountService extends BaseService {

    @Inject
    private LoginService loginService;
    
    public UserAccount createNewUserAndActivate() {
	testUtils.defaultConfigurations();
	
	pages.TopNavigation().login();
	
	waitUtils.waitSeconds(3);
	
	pages.Login().clickCreateAccount();

	waitUtils.waitForLoad();
	
	UserAccount user = userGenerator.createNewUser();
	pages.CreateAccount().fillInputFieldBy(user).save();

	waitUtils.waitSeconds(2);
	
	String tokenForActivation = activationEmailLink.getTokenUrlForAccountActivation(user.getEmail());
	testUtils.getPageByUrl(tokenForActivation);

	assertThat(testUtils.getPortletTextByClass("vectorUserManagement"), equalTo("The activation of your account was successful."));
	assertThat(pages.getPageBy(InfoPage.class).getTitle(), equalTo("Activation"));

	user.setActivated(true);
	testUtils.homePage();
	return user;
    }
    
    public void deleteUser(UserAccount user) {
	testUtils.defaultConfigurations();
	
	loginService.toLoginPage(user);	
	
	TabsNavigation tabs = pages.getPageBy(TabsNavigation.class);
	tabs.myAccountPage();

	TabsMenu menuOfTab = pages.getPageBy(TabsMenu.class);
	menuOfTab.deleteAccount();

	pages.getPageBy(DeleteAccountPage.class).delete();

	assertTrue(testUtils.containsText("Welcome to the Vector Customer Portal"));
	assertTrue(pages.TopNavigation().isLoginButtonVisible());
	
	testUtils.defaultConfigurations();
    }    
}
