package com.vector.extranet.selenium.test.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.AccountPage;
import com.vector.extranet.selenium.pages.CreateAccountPage;

public class CreateAccountPageTest extends BaseTest {

    @BeforeMethod
    public void beforeTest() {
	testUtils.homePage();
    }
    
    @Test
    public void canNotRegisterWithUnavailableEmailAddress() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	pages.getPageBy(CreateAccountPage.class).fillInputFieldBy(getUser()).email("asdfsdffsf").save();

	assertTrue(testUtils.containsText(AccountPage.invalidEmailMessage));
	assertTrue(testUtils.existsError("email"));
    }

    @Test
    public void canNotRegisterWhenNameFieldsEmpty() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	CreateAccountPage createAccountPage = pages.getPageBy(CreateAccountPage.class);

	createAccountPage.fillInputFieldBy(getUser()).firstName("").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.existsError("firstName"));

	createAccountPage.firstName("FirstName").lastName("").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.existsError("lastName"));
    }

    @Test
    public void canNotRegisterWhenCountryEmpty() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	CreateAccountPage createAccountPage = pages.getPageBy(CreateAccountPage.class);

	createAccountPage.fillInputFieldBy(getUser()).country("0").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.existsError("country"));
    }

    @Test
    public void canNotRegisterWhenUserAlreadyExist() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	CreateAccountPage createAccountPage = pages.getPageBy(CreateAccountPage.class);

	UserAccount registeredUser = userGenerator.getActiveUser();

	createAccountPage.fillInputFieldBy(registeredUser).save();

	assertTrue(testUtils.containsText(AccountPage.invalidEmailMessage));
    }

    @Test
    public void showCompanyAndDivisionWhenBusinessAddressChecked() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	CreateAccountPage createAccountPage = pages.getPageBy(CreateAccountPage.class);

	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	assertTrue(createAccountPage.isBusinessAddressChecked());
	assertTrue(createAccountPage.isCompanyExist());
	assertTrue(createAccountPage.isDivisionExist());
    }

    @Test
    public void doNotShowCompanyAndDivisionWhenBusinessAddressNotChecked() {
	pages.TopNavigation().login();
	pages.Login().clickCreateAccount();

	CreateAccountPage createAccountPage = pages.getPageBy(CreateAccountPage.class);

	createAccountPage.business();

	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	assertFalse(createAccountPage.isBusinessAddressChecked());
	assertFalse(createAccountPage.isCompanyExist());
	assertFalse(createAccountPage.isDivisionExist());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.createNewUser();
    }

}
