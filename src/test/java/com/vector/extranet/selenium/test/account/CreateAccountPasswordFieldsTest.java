package com.vector.extranet.selenium.test.account;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.AccountPage;
import com.vector.extranet.selenium.pages.CreateAccountPage;
import com.vector.extranet.selenium.test.common.PasswordFieldsTest;

public class CreateAccountPasswordFieldsTest extends PasswordFieldsTest {

    @Override
    public void goToPage() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();
	pages.getPageBy(CreateAccountPage.class).email(getUser().getEmail());
    }

    @Test
    public void canNotRegisterWhenPasswordFieldsEmpty() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	CreateAccountPage createAccountPage = pages.getPageBy(CreateAccountPage.class);

	createAccountPage.fillInputFieldBy(getUser()).password("").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.containsText("The entered passwords do not match."));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));

	createAccountPage.password(getUser().getPasswordRepeat()).passwordRepeat("").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.containsText("The entered passwords do not match."));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.createNewUser();
    }

}
