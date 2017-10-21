package com.vector.extranet.selenium.test.common;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.InfoPage;
import com.vector.extranet.selenium.pages.ResetPasswordPage;
import com.vector.extranet.selenium.pages.ResetPasswordRequestPage;

public class ResetPasswordRequestTest extends BaseTest {

    private final String newPassword = "newPassword!123";
    
    @BeforeClass
    public void beforeTest() {
	pages.TopNavigation().login();
	loginAs(getUser());
	
	testUtils.defaultConfigurations();
    }

    @Test(description = "Check, if the page is Reset Password?")
    public void isCorrectPage() {
	pages.TopNavigation().login();
	pages.Login().clickResetPassword();
	
	waitUtils.waitForLoad();
	
	assertTrue(pages.getPageBy(ResetPasswordRequestPage.class).isPageCorrect());
    }

    @Test(description = "Check, if can I send a reset password request with a non-registered e-mail")
    public void canRequestResetPasswordForNotRegisteredUser() {
	pages.TopNavigation().login();
	pages.Login().clickResetPassword();

	pages.getPageBy(ResetPasswordRequestPage.class).email("notRegisteredUser@notRegistered.com").submit();

	assertThat(pages.getPageBy(InfoPage.class).getAlertSuccessText(), equalTo("Your request completed successfully."));
    }

    @Test(description = "Check the info text is correct, after reset password request")
    public void isInfoTextCorrect() {
	pages.TopNavigation().login();
	pages.Login().clickResetPassword();

	pages.getPageBy(ResetPasswordRequestPage.class).email("notRegisteredUser@notRegistered.com").submit();

	waitUtils.waitUntilPresenceOfElementLocatedByXpath("//div[@class='portlet-body']/div[@class='vectorResetPassword']//p");
	
	assertThat(testUtils.getPortletTextByClass("vectorResetPassword"), equalTo(
		"You'll receive an e-mail shortly to reset your password. If you don't receive a mail, please contact webmaster.portal@vector.com."));
    }

    @Test(description = "Check, if can I send a reset password request with an invalid password")
    public void canNotRequestWithInvalidMailAddress() {
	pages.TopNavigation().login();
	pages.Login().clickResetPassword();

	pages.getPageBy(ResetPasswordRequestPage.class).email("fsdfsdf").submit();

	assertTrue(testUtils.containsText("The entered email address seems to be invalid. Please check the address."));
	assertTrue(testUtils.containsText("Your request failed to complete."));
    }

    @Test(description = "Check, if can I send a reset password request and reset my password ") 
    public void canResetPassword() {
	pages.TopNavigation().login();
	pages.Login().clickResetPassword();

	pages.getPageBy(ResetPasswordRequestPage.class).resetPassword(getUser().getEmail());

	assertThat(pages.getPageBy(InfoPage.class).getAlertSuccessText(), equalTo("Your request completed successfully."));
	
	String tokenForChangePassword = activationEmailLink.getTokenUrlForResetPassword(getUser().getEmail());
	testUtils.getPageByUrl(tokenForChangePassword);

	ResetPasswordPage resetPasswordPage = pages.getPageBy(ResetPasswordPage.class);
	resetPasswordPage.password(newPassword).passwordRepeat(newPassword).submit();
	
	assertThat(pages.getPageBy(InfoPage.class).getAlertSuccessText(), equalTo("Your request completed successfully."));
	
	getUser().setPassword(newPassword);
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return accountService.createNewUserAndActivate();
    }

}
