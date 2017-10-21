package com.vector.extranet.selenium.test.quickshare;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.vector.extranet.selenium.pages.FileToolbar;
import com.vector.extranet.selenium.pages.FirstFolderRow;
import com.vector.extranet.selenium.pages.QuickShareOptions;
import com.vector.extranet.selenium.pages.ShareLinkPopup;
import com.vector.extranet.selenium.service.MemberAreaService;

@Listeners(FailTestListener.class)
public class ShareLinkTest extends BaseTest {

    @Inject
    private MemberAreaService memberAreaService;

    private String folderName;
    private String memberAreaName;

    @BeforeClass
    public void beforeTests(ITestContext context) {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaName = memberAreaService.createMemberArea(this.getTestClassName());

	folderName = String.format("%s_%s", "AAA", RandomStringUtils.randomAlphabetic(20));
    }

    @AfterClass
    public void afterTests() {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaService.deleteMemberArea(memberAreaName);
    }

    @Test
    public void canCreateShareLink() {
	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.memberAreaLink(memberArea);

	memberAreaService.createNewFolder(folderName);

	FirstFolderRow createdFolder = pages.getPageBy(FirstFolderRow.class);
	createdFolder.clickFolderName();

	testUtils.setFileDetector();

	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/Ferrari.jpg");

	fileToolbar.up();

	waitUtils.waitUntilClickable(".//vec-dl-ui-entry-list-item//div[@class='vec-dl-ui-name']/a");

	createdFolder.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Share')]");

	QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
	quickShareOptions.clickShare();

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//div[@class='vec-dl-ui-share-folder-dlg']");

	ShareLinkPopup shareLinkPopup = pages.getPageBy(ShareLinkPopup.class);
	shareLinkPopup.clickCreate();

	createdFolder.clickSharedFolderIcon();

	waitUtils.waitUntil(".//div[@class='vec-app-dialog-content']");

	String shareUrl = shareLinkPopup.getShareUrlText();

	testUtils.directLogOut();

	testUtils.getPageByUrl(shareUrl);

	assertThat(testUtils.findElementBy(".//h2[@class='shared-folder-name']").getText().trim(), equalTo(folderName));

	assertTrue(testUtils.containsTextUnder(".//*[@id='fileList']", "Ferrari.jpg"));

	testUtils.findElementBy(".//*[@id='fileList']//*[contains(text(),'Ferrari.jpg')]").click();

	assertTrue(testUtils.isPageLoaded());
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }

}
