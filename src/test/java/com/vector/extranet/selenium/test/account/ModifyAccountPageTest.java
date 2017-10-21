package com.vector.extranet.selenium.test.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.AccountPage;
import com.vector.extranet.selenium.pages.ModifyAccountPage;
import com.vector.extranet.selenium.pages.TabsMenu;
import com.vector.extranet.selenium.pages.TabsNavigation;

public class ModifyAccountPageTest extends BaseTest {

    @BeforeClass
    public void beforeAllTests() {
	loginService.toLoginPage(getUser());
	
	TabsNavigation tabsNavigation = pages.getPageBy(TabsNavigation.class);

	tabsNavigation.myAccountPage();
	
	TabsMenu tabsMenu = pages.getPageBy(TabsMenu.class);
	tabsMenu.modifyAccount();
    }
    
    @Test
    public void canNotModifyWithUnavailableEmailAddress() {
	ModifyAccountPage modifyAccountPage = pages.getPageBy(ModifyAccountPage.class);

	modifyAccountPage.email("sdfsdf").save();

	assertTrue(testUtils.containsText(AccountPage.invalidEmailMessage));
	assertTrue(testUtils.existsError("email"));
    }

    @Test
    public void canNotModifyWhenUserAlreadyExist() {
	ModifyAccountPage modifyAccountPage = pages.getPageBy(ModifyAccountPage.class);

	UserAccount user = userGenerator.getAnotherActiveUser();
	modifyAccountPage.email(user.getEmail()).save();

	assertTrue(testUtils.containsText(AccountPage.invalidEmailMessage));
	assertTrue(testUtils.existsError("email"));
    }

    @Test
    public void canNotModifyWhenNameFieldsEmpty() {
	ModifyAccountPage modifyAccountPage = pages.getPageBy(ModifyAccountPage.class);

	modifyAccountPage.firstName("FirstName").lastName("").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.existsError("lastName"));

	modifyAccountPage.firstName("").lastName("LastName").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.existsError("firstName"));
    }

    @Test
    public void canNotModifyWhenCountryEmpty() {
	ModifyAccountPage modifyAccountPage = pages.getPageBy(ModifyAccountPage.class);

	modifyAccountPage.country("0").save();

	assertTrue(testUtils.containsText(AccountPage.requiredFieldsMessage));
	assertTrue(testUtils.existsError("country"));
    }

    @Test
    public void showCompanyAndDivisionWhenBusinessAddressChecked() {
	ModifyAccountPage modifyAccountPage = pages.getPageBy(ModifyAccountPage.class);

	if (!modifyAccountPage.isBusinessAddressChecked()) {
	    modifyAccountPage.business();
	}

	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	assertTrue(modifyAccountPage.isCompanyExist());
	assertTrue(modifyAccountPage.isDivisionExist());
    }

    @Test
    public void doNotShowCompanyAndDivisionWhenBusinessAddressNotChecked() {
	ModifyAccountPage modifyAccountPage = pages.getPageBy(ModifyAccountPage.class);

	if (modifyAccountPage.isBusinessAddressChecked()) {
	    modifyAccountPage.business();
	}

	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	assertFalse(modifyAccountPage.isCompanyExist());
	assertFalse(modifyAccountPage.isDivisionExist());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getActiveUser();
    }

}
