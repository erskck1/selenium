package com.vector.extranet.selenium.test.common;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.pages.PasswordFields;

public abstract class PasswordFieldsTest extends BaseTest {

    public abstract void goToPage();

    @Test(description = "if the entered and re-entered passwords are not match, show the text : 'The entered passwords do not match.'")
    public void showWarningWhenPasswordsNotMatch() {
	goToPage();

	PasswordFields passwordFieldsPage = pages.getPageBy(PasswordFields.class);
	passwordFieldsPage.password("notMatchedPassword1").passwordRepeat("notMatchedPassword2").save();

	assertTrue(testUtils.containsText("The entered passwords do not match."));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));
    }

    @Test(description = "if the length of the entered password is smaller than eight, show the text : 'The entered password does not meet the required security constraints'")
    public void showWarningWhenPasswordsLengthLessThanEight() {
	goToPage();

	PasswordFields passwordFieldsPage = pages.getPageBy(PasswordFields.class);
	passwordFieldsPage.password("123Abc!").passwordRepeat("123Abc!").save();

	assertTrue(testUtils.containsText("The entered password does not meet the required security constraints"));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));
    }

    @Test(description = "if the entered password is not include at least one number, show the text : 'The entered password does not meet the required security constraints'")
    public void showWarningWhenPasswordsIncludeNotAtLeastOneNumber() {
	goToPage();

	PasswordFields passwordFieldsPage = pages.getPageBy(PasswordFields.class);
	passwordFieldsPage.password("Abcdefg!").passwordRepeat("Abcdefg!").save();

	assertTrue(testUtils.containsText("The entered password does not meet the required security constraints"));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));
    }

    @Test(description = "if the entered password is not include at least one capital, show the text : 'The entered password does not meet the required security constraints'")
    public void showWarningWhenPasswordsIncludeNotAtLeastOneCapital() {
	goToPage();

	PasswordFields passwordFieldsPage = pages.getPageBy(PasswordFields.class);
	passwordFieldsPage.password("abcdefg!123").passwordRepeat("abcdefg!123").save();

	assertTrue(testUtils.containsText("The entered password does not meet the required security constraints"));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));
    }

    @Test(description = "if the entered password is not include at least one special character, show the text : 'The entered password does not meet the required security constraints'")
    public void showWarningWhenPasswordsIncludeNotSpecialCharacter() {
	goToPage();

	PasswordFields passwordFieldsPage = pages.getPageBy(PasswordFields.class);
	passwordFieldsPage.password("Abcdefg123").passwordRepeat("Abcdefg123").save();

	assertTrue(testUtils.containsText("The entered password does not meet the required security constraints"));
	assertTrue(testUtils.existsError("password"));
	assertTrue(testUtils.existsError("passwordRepeat"));
    }

    @Test(enabled = false, description = "if the entered password is include part of the email, show the text :'The entered password contains parts of the mail address.'") // Bug
																					    // -
																					    // muss
																					    // fixed
																					    // werden
    public void showWarningWhenPasswordsIncludePartOfEmail() {
	goToPage();

	String partOfEmail = StringUtils.split(prepareUserOfTheTest().getEmail(), "@")[0];
	String password = String.format("%sA1!b", partOfEmail);

	PasswordFields passwordFieldsPage = pages.getPageBy(PasswordFields.class);
	passwordFieldsPage.password(password).passwordRepeat(password).save();

	assertTrue(testUtils.containsText("The entered password contains parts of the mail address."));
    }
}
