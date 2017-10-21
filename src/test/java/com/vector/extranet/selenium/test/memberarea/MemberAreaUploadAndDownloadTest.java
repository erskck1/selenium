package com.vector.extranet.selenium.test.memberarea;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.FileToolbar;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberAreaUploadAndDownloadTest extends BaseTest {

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

    @Test(description = "This Test check that can user upload a file to member area")
    public void _1_canUploadFile() {

	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@id='member-area-form']");

	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.memberAreaLink(memberArea);

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'File Upload')]");

	testUtils.setFileDetector();

	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/Ferrari.jpg");

	assertTrue(testUtils.containsTextUnder("//div[@class='vec-app-table vec-dl-ui-entry-list-table']", "Ferrari.jpg"));
    }

    @Test(description = "This Test check that can user upload a file to member area")
    public void _2_canUploadFileOver50Mb() {

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'File Upload')]");

	testUtils.findElementBy(".//input[@name='file']").sendKeys("./resources/over50MbData");

	waitUtils.waitUntil(".//*[@class='vec-app-dialog-content']");
	testUtils.findElementBy(".//*[@class='vec-app-buttons']/button[contains(text(),'OK')]").click();

	waitUtils.waitUntilTextToBePresentInElement("//div[@class='vec-app-table vec-dl-ui-entry-list-table']", "over50MbData", 150);

	assertTrue(testUtils.containsTextUnder("//div[@class='vec-app-table vec-dl-ui-entry-list-table']", "over50MbData"));
    }

    @Test(description = "This Test check that can user download the uploaded file from member area")
    public void _3_canDownloadFile() throws Exception {
	WebElement uploadedFile = testUtils.findElementBy(".//vec-dl-ui-entry-list-item[2]/div[1]//a");

	uploadedFile.click();

	assertTrue(testUtils.isPageLoaded());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }

}
