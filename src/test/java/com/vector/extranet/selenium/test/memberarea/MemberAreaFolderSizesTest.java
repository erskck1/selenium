package com.vector.extranet.selenium.test.memberarea;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.FileToolbar;
import com.vector.extranet.selenium.pages.FirstFolderRow;
import com.vector.extranet.selenium.pages.SecondFolderRow;
import com.vector.extranet.selenium.pages.TabsNavigation;

public class MemberAreaFolderSizesTest extends BaseTest {

    String folderName1;
    String folderName2;

    @BeforeClass
    public void beforeTests() {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaName = memberAreaService.createMemberArea(this.getTestClassName());
	
	folderName1 = String.format("%s_%s", "AAA", RandomStringUtils.randomAlphabetic(20));
	folderName2 = String.format("%s_%s", "BBB", RandomStringUtils.randomAlphabetic(20));
    }
    
    private String memberAreaName;

    @AfterClass(alwaysRun=true)
    public void afterTests() {
	memberAreaService.deleteMemberArea(memberAreaName);
    }

    @Test(description = "Test checks that are the folder sizes correct")
    public void areTheFolderSizesCorrect() {
	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@id='member-area-form']");

	TabsNavigation tabsNavigation = pages.getPageBy(TabsNavigation.class);
	tabsNavigation.memberAreaAdministration();

	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.memberAreaLink(memberArea);

	memberAreaService.createNewFolder(folderName1);

	waitUtils.waitUntil(String.format(".//vec-dl-ui-entry-list-item[1]//a[contains(text(),'%s')]", folderName1));

	FirstFolderRow firstFolder = pages.getPageBy(FirstFolderRow.class);
	firstFolder.clickFolderName();

	testUtils.setFileDetector();

	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/Ferrari.jpg");

	fileToolbar.up();

	waitUtils.waitUntil(String.format(".//vec-dl-ui-entry-list-item[1]//a[contains(text(),'%s')]", folderName1));

	memberAreaService.createNewFolder(folderName2);

	waitUtils.waitUntil(String.format(".//vec-dl-ui-entry-list-item[2]//a[contains(text(),'%s')]", folderName2));

	SecondFolderRow secondFolder = pages.getPageBy(SecondFolderRow.class);
	secondFolder.clickFolderName();

	fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/Porsche.jpg");

	fileToolbar.up();

	waitUtils.waitUntil(String.format(".//vec-dl-ui-entry-list-item[2]//a[contains(text(),'%s')]", folderName2));

	fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.folderSizes();

	waitUtils.waitUntil(".//div[@class='vec-app-dialog-content']");

	assertTrue(testUtils.containsTextUnder(".//*[@class='ng-scope']/li[1]", folderName1));
	assertTrue(testUtils.containsTextUnder(".//*[@class='ng-scope']/li[1]", "128 KB"));
	assertTrue(testUtils.containsTextUnder(".//*[@class='ng-scope']/li[2]", folderName2));
	assertTrue(testUtils.containsTextUnder(".//*[@class='ng-scope']/li[2]", "120 KB"));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }
}
