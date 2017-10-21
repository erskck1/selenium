package com.vector.extranet.selenium.test.memberarea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberAreaTest extends BaseTest {

    private String memberAreaName;

    @BeforeClass
    public void beforeTest() {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaName = memberAreaService.createMemberArea(this.getTestClassName());
    }
    
    @AfterClass
    public void afterTests() {
	memberAreaService.deleteMemberArea(memberAreaName);
    }
    
    @Test
    public void _1_canAddMemberToMemberArea() {
	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);

	UserAccount userForAdding = userGenerator.getActiveUser();
	memberAreaService.addMemberToMemberArea(memberArea, userForAdding.getEmail());

	assertTrue(testUtils.containsText(".//*[@class='vec-app-col ng-binding' and contains(text(),'%s')]", userForAdding.getEmail()));
    }

    @Test
    public void _2_canRemoveMemberFromMemberArea() {
	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@class='vec-ma-ua-add-members-dlg']");

	WebElement removeButton = testUtils.findElementBy(".//*[@class='vec-ma-ua-user-action vec-app-icon-remove ng-binding']");
	removeButton.click();

	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@class='vec-app-col ng-binding' and contains(text(),'%s')]");

	assertFalse(testUtils.containsText(userGenerator.getActiveUser().getEmail()));

    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }

}
