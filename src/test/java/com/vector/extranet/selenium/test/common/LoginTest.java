package com.vector.extranet.selenium.test.common;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends BaseTest {

    @BeforeMethod
    public void beforeSub() {
	testUtils.homePage();
	pages.TopNavigation().login();
    }

    @Test(description = "if the e-mail is registered but the password is wrong, you can't login")
    public void _1_canNotLoginWithFalsePassword() {
	loginAs(getUser().getEmail(), "falsePassword!1");

	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.requestFailed")));
	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.authenticationFailed")));
    }

    @Test(description = "if the email is not registered, can't login")
    public void _2_canNotLoginWithFalseEmail() {
	loginAs("asdfasf343@vector.com", getUser().getPassword());

	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.requestFailed")));
	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.authenticationFailed")));
    }

    @Test(description = "if the email and password wrong, you can't login")
    public void _3_canNotLoginWithFalseEmailAndPassword() {
	loginAs("asdfasf343@sdgfdg.com", "dggdg869�$!");

	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.requestFailed")));
	assertTrue(testUtils.containsText(config.getMessageBy("loginPage.authenticationFailed")));
    }

    @Test
    public void _4_canLogin() {
	loginAs(getUser());

	assertTrue(pages.TopNavigation().isLoginSuccess());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getActiveUser();
    }
}
