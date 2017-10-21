package com.vector.extranet.selenium.test.quickshare;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.framework.FailTestListener;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.CreateNewDropZonePopup;
import com.vector.extranet.selenium.pages.DropZonePage;
import com.vector.extranet.selenium.pages.FirstFolderRow;
import com.vector.extranet.selenium.pages.QuickShareOptions;
import com.vector.extranet.selenium.service.MemberAreaService;

@Listeners(FailTestListener.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DropzoneTest extends BaseTest {

    @Inject
    private MemberAreaService memberAreaService;

    private String folderName;
    private String dropzoneUrl;
    private String memberAreaName;

    @BeforeClass
    public void beforeTests(ITestContext context) {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaName = memberAreaService.createMemberArea(this.getTestClassName());

	folderName = String.format("%s_%s", "AAA", RandomStringUtils.randomAlphabetic(20));

	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.addMemberToMemberArea(memberArea, getUser().getEmail());

	testUtils.back();

	memberArea = memberAreaService.findMemberAreaBy(memberAreaName);

	memberAreaService.memberAreaLink(memberArea);

	memberAreaService.createNewFolder(folderName);
    }

    @AfterClass
    public void afterTests() {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaService.deleteMemberArea(memberAreaName);
    }

    @Test
    public void _1_isExistCreateNewDropZonePopup() {
	FirstFolderRow createdFolder = pages.getPageBy(FirstFolderRow.class);

	createdFolder.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Create Drop Zone')]");

	QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
	quickShareOptions.clickCreateDropZone();

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//div[@class='vec-dl-ui-drop-zone-dlg']");

	assertTrue(testUtils.isElementExistByXpath(".//div[@class='vec-dl-ui-drop-zone-dlg']"));
    }

    @Test
    public void _2_canCreateDropZone() {
	CreateNewDropZonePopup createDropZonePopup = pages.getPageBy(CreateNewDropZonePopup.class);
	waitUtils.waitSeconds(3);
	createDropZonePopup.clickCreate();

	assertTrue(testUtils.isElementExistByXpath(".//vec-dl-ui-entry-list-item[1]//div[@class='vec-dl-ui-dropzone vec-dl-ui-show']"));
    }

    @Test
    public void _3_canOpenTheDropZoneUrl() {
	FirstFolderRow dropZoneFolder = pages.getPageBy(FirstFolderRow.class);
	dropZoneFolder.clickDropZoneIcon();

	waitUtils.waitUntil(".//div[@class='vec-app-dialog-content']");

	CreateNewDropZonePopup dropZonePopup = pages.getPageBy(CreateNewDropZonePopup.class);
	dropzoneUrl = dropZonePopup.getDropzoneUrlText();

	testUtils.getPageByUrl(dropzoneUrl);

	assertThat(testUtils.getPageTitle(), equalTo("Drop Zone - Customer Portal"));
    }

    @Test
    public void _4_canUploadFileToDropZone() {
	testUtils.setFileDetector();

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//input[@type='file']");

	DropZonePage dropZonePage = pages.getPageBy(DropZonePage.class);
	dropZonePage.uploadFile("./resources/Porsche.jpg");

	assertThat(testUtils.containsTextUnder(".//*[@id='fileList']", "Porsche.jpg"), equalTo(true));

	dropZonePage.emailInput("aaaa@aaa.com");
	dropZonePage.termsOfUse();
	dropZonePage.uploadButton();

	waitUtils.waitUntil(".//*[@class='upload-shared']");

	assertThat(testUtils.isElementExistByXpath(".//*[@class='upload-shared']"), equalTo(true));

    }

    @Test
    public void _5_canUploadFileWithoutLogin() {
	testUtils.directLogOut();

	testUtils.getPageByUrl(dropzoneUrl);

	testUtils.setFileDetector();

	DropZonePage dropZonePage = pages.getPageBy(DropZonePage.class);
	dropZonePage.uploadFile("./resources/Ferrari.jpg");

	dropZonePage.emailInput("aaaa@aaa.com");
	dropZonePage.termsOfUse();
	dropZonePage.uploadButton();

	waitUtils.waitUntilInvisibilityOfElementBy(".//img[@src='/vector-portal-2-theme/images/common/delete.png']");

	assertThat(testUtils.containsTextUnder(".//*[@id='fileList']", "Ferrari.jpg"), equalTo(true));
	assertThat(testUtils.isElementExistByXpath(".//img[@src='/vector-portal-2-theme/images/common/delete.png']"), equalTo(false));
    }

    @Test
    public void _6_canAddedMemberSeeDropZoneFolder() {
	loginService.toLoginPage(getUser());

	waitUtils.waitUntilClickable(".//*[@id='main-menu']//a");

	WebElement userMemberArea = testUtils
		.findElementBy(String.format(".//*[@id='main-menu']/li/a[@href=contains(text(),'%s')]", memberAreaName));
	userMemberArea.click();

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//vec-dl-ui-entry-list-item[1]//div[@class='vec-dl-ui-name']/a");

	assertTrue(testUtils.containsTextUnder(".//vec-dl-ui-entry-list-item[1]", folderName));

    }

    @Test
    public void _7_canAddedMemberSeeSharedFiles() {
        FirstFolderRow folder = pages.getPageBy(FirstFolderRow.class);
        folder.clickFolderName();
    
        waitUtils.waitUntil(".//*[@class='vec-dl-ui-segment vec-dl-ui-folder ng-scope']");
    
        assertThat(testUtils.containsTextUnder(".//div[@class='vec-app-table vec-dl-ui-entry-list-table']", "Ferrari.jpg"), equalTo(true));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return accountService.createNewUserAndActivate();
    }

}
