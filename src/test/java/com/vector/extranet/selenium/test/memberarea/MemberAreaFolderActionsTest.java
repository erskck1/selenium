package com.vector.extranet.selenium.test.memberarea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.FileToolbar;
import com.vector.extranet.selenium.pages.FirstFolderRow;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberAreaFolderActionsTest extends BaseTest {

    private String folderName;
    private String memberAreaName;

    @BeforeClass
    public void beforeTests() {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaName = memberAreaService.createMemberArea(this.getTestClassName());
	folderName = String.format("%s_%s", "AAA", RandomStringUtils.randomAlphabetic(20));
    }

    @AfterClass
    public void afterTests() {
	memberAreaService.deleteMemberArea(memberAreaName);
    }

    @Test(description = "Test checks that can create an empty folder in member area")
    public void _1_canCreateFolder() {

	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@id='member-area-form']");

	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.memberAreaLink(memberArea);

	memberAreaService.createNewFolder(folderName);

	assertTrue(testUtils.containsTextUnder(".//*[@class='vec-app-table vec-dl-ui-entry-list-table']", folderName));

    }

    @Test(description = "Test checks that can move a file to a folder")
    public void _2_canMoveFileToFolder() {
	testUtils.setFileDetector();

	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/Ferrari.jpg");
	fileToolbar.uploadFile("./resources/Porsche.jpg");

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//vec-dl-ui-entry-list-item[3]/div[1]/div[1]");
	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//vec-dl-ui-entry-list-item[4]/div[1]/div[1]");

	waitUtils.waitSeconds(2);
	
	WebElement uploadedFile1 = testUtils.findElementBy(".//vec-dl-ui-entry-list-item[3]/div[1]/div[1]");
	WebElement uploadedFile2 = testUtils.findElementBy(".//vec-dl-ui-entry-list-item[4]/div[1]/div[1]");

	uploadedFile1.click();
	uploadedFile2.click();

	waitUtils.waitSeconds(2);
	
	fileToolbar.move();

	waitUtils.waitUntil(".//*[@class='vec-app-dialog-content']");

	waitUtils.waitSeconds(2);
	
	WebElement moveTofolder = testUtils.findElementBy(".//ul[@class='ng-scope']/li[1]/span[1]");
	moveTofolder.click();

	waitUtils.waitSeconds(5);

	FirstFolderRow movedFolder = pages.getPageBy(FirstFolderRow.class);
	movedFolder.clickFolderName();

	waitUtils.waitSeconds(3);

	assertTrue(testUtils.containsText("Ferrari.jpg"));
	assertTrue(testUtils.containsText("Porsche.jpg"));

    }

    @Test(description = "Test checks that can delete a folder in member area")
    public void _3_canDeleteFile() {
	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.up();

	waitUtils.waitForLoad();

	FirstFolderRow folder = pages.getPageBy(FirstFolderRow.class);
	folder.clickCheckbox();

	fileToolbar.delete();

	waitUtils.waitUntil(".//*[@class='vec-app-dialog vec-app-dialog-modal level0']");

	WebElement applyDeleteButton = testUtils.findElementBy(".//div[@class='vec-app-buttons']/button[1]");
	applyDeleteButton.click();

	assertFalse(testUtils.containsTextUnder(".//div[@class='vec-app-table vec-dl-ui-entry-list-table']", memberAreaName));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }

}
