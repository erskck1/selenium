package com.vector.extranet.selenium.test.account;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.CreateAccountPage;
import com.vector.extranet.selenium.pages.InfoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateAccountTest extends BaseTest {

    @Test
    public void _1_canCreateAccount() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	pages.getPageBy(CreateAccountPage.class).fillInputFieldBy(getUser()).save();

	waitUtils.waitUntilPresenceOfElementLocatedByXpath("//div[@class='portlet-body']/div[@class='vectorUserManagement']//p");
	
	assertThat(testUtils.getPortletTextByClass("vectorUserManagement"),
		equalTo("Your account has been created. You will get a confirmation mail containing your entered data."));
    }

    @Test
    public void _2_canNotLoginRegisteredButNotActivatedAccount() {
	pages.TopNavigation().login();

	pages.Login().loginAs(getUser().getEmail(), getUser().getPassword());

	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.requestFailed")));
	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.authenticationFailed")));
    }

    @Test
    public void _3_canActivateAccount() {
	String token = activationEmailLink.getTokenUrlForAccountActivation(getUser().getEmail());

	testUtils.getPageByUrl(token);

	assertThat(testUtils.getPortletTextByClass("vectorUserManagement"), equalTo("The activation of your account was successful."));
	assertThat(pages.getPageBy(InfoPage.class).getTitle(), equalTo("Activation"));
	
	getUser().setActivated(true);
    }

    @Test
    public void _4_canLoginActivatedAccount() {
	pages.TopNavigation().login();

	pages.Login().loginAs(getUser().getEmail(), getUser().getPassword());

	waitUtils.waitForLoad();
	
	assertTrue(pages.TopNavigation().isLoginSuccessAs(getUser().getNameAndSurname()));
	assertTrue(testUtils.isElementExistByXpath(".//*[@id='portlet_terms-of-use']"));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.createNewUser();
    }
}
