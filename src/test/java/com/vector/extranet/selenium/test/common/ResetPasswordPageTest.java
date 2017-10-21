package com.vector.extranet.selenium.test.common;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.AccountPage;
import com.vector.extranet.selenium.pages.PasswordFields;
import com.vector.extranet.selenium.pages.ResetPasswordRequestPage;

public class ResetPasswordPageTest extends PasswordFieldsTest {

    private String token;

    @Override
    public void goToPage() {
	if (token == null) {
	    pages.TopNavigation().login();
	    pages.Login().clickResetPassword();

	    pages.getPageBy(ResetPasswordRequestPage.class).resetPassword(getUser().getEmail());
	    testUtils.getPageByUrl(getToken(getUser().getEmail()));
	} else {
	    testUtils.getPageByUrl(token);
	}
    }

    @Test
    public void canNotResetWhenPasswordFieldsEmpty() {
	goToPage();

	PasswordFields passwordFieldsPage = pages.getPageBy(PasswordFields.class);

	passwordFieldsPage.password("").passwordRepeat("1234Abc!").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.containsText("The entered passwords do not match."));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));

	passwordFieldsPage.password("1234Abc!").passwordRepeat("").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.containsText("The entered passwords do not match."));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));
    }

    private String getToken(String email) {
	token = activationEmailLink.getTokenUrlForResetPassword(email);
	return token;
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getActiveUser();
    }

}
