package com.vector.extranet.selenium.test.memberarea;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.framework.FailTestListener;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.Paging;

@Listeners(FailTestListener.class)
public class CreateMemberAreaTest extends BaseTest {

    private String memberAreaName;

    @BeforeClass
    public void beforeTest(ITestContext context) {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaName = memberAreaService.createMemberArea(this.getTestClassName());
    }

    @Test
    public void canCreateNewMemberAreaAndDelete() {
	waitUtils.waitUntilClickable(".//*[@name='admin-light-member-area-table_length']");

	Paging pagination = pages.getPageBy(Paging.class);
	pagination.entries("100");

	assertTrue(testUtils.containsText("Successfully created member area"));
	assertTrue(testUtils.containsText(memberAreaName));

	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.deleteMemberAreaButton(memberArea);

	waitUtils.waitSeconds(3);
	assertFalse(testUtils.containsText(memberAreaName));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }

}
