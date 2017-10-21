package com.vector.extranet.selenium.test.quickshare;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.FileToolbar;
import com.vector.extranet.selenium.pages.QuickShareOptions;
import com.vector.extranet.selenium.pages.SecondFolderRow;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileVersionTest extends BaseTest {

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
    public void _1_whenAFileUploadedFirstIsVersion1() {
	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@id='member-area-form']");

	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.memberAreaLink(memberArea);

	waitUtils.waitUntilPresenceOfElementLocatedByXpath(".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'File Upload')]");

	testUtils.setFileDetector();

	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/Ferrari.jpg");

	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@class='vec-dl-ui-file-name ng-binding']");

	SecondFolderRow uploadedFile = pages.getPageBy(SecondFolderRow.class);
	uploadedFile.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Properties')]");

	QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
	quickShareOptions.clickProperties();

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-dlg']");

	WebElement version = testUtils.findElementBy(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a");

	assertThat(version.getText().trim(), equalTo("1.0"));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']", "129.0 kB"), equalTo(true));
    }

    @Test
    public void _2_whenAgainSameFileUploadWithoutReplace() {
	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/Ferrari.jpg");

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-exists-dlg']");

	WebElement okButton = testUtils.findElementBy(".//div[@class='vec-app-buttons']/button[contains(text(),'OK')]");

	okButton.click();

	waitUtils.waitUntilInvisibilityOfElementBy(".//div[@class='vec-dl-ui-file-exists-dlg']");
	waitUtils.waitUntilInvisibilityOfElementBy(".//div[@class='vec-dl-ui-file-name ng-binding']");
	
	SecondFolderRow uploadedFile = pages.getPageBy(SecondFolderRow.class);
	uploadedFile.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Properties')]");

	QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
	quickShareOptions.clickProperties();

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-dlg']");

	WebElement version = testUtils.findElementBy(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a");

	assertThat(version.getText().trim(), equalTo("1.0"));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']", "129.0 kB"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a", "2.0"), equalTo(false));
    }

    @Test
    public void _3_whenAgainSameFileUploadWithReplace() {
	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.uploadFile("./resources/upload/Ferrari.jpg");

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-exists-dlg']");

	testUtils.findElementBy(".//*[@class='vec-app-row vec-dl-ui-select-files-row']//input").click();

	WebElement okButton = testUtils.findElementBy(".//div[@class='vec-app-buttons']/button[contains(text(),'OK')]");

	okButton.click();

	waitUtils.waitUntilInvisibilityOfElementBy(".//div[@class='vec-dl-ui-file-exists-dlg']");
	waitUtils.waitUntilInvisibilityOfElementBy(".//div[@class='vec-dl-ui-file-name ng-binding']");
	
	SecondFolderRow uploadedFile = pages.getPageBy(SecondFolderRow.class);
	uploadedFile.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Properties')]");

	QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
	quickShareOptions.clickProperties();

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-dlg']");

	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']", "129.0 kB"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']", "50.7 kB"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a", "1.0"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a", "2.0"), equalTo(true));
    }

    @Test
    public void _4_canRevertTheFileToOldVersion() {
	SecondFolderRow uploadedFile = pages.getPageBy(SecondFolderRow.class);
	uploadedFile.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Properties')]");

	QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
	quickShareOptions.clickProperties();

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-dlg']");

	waitUtils.waitUntilClickable(".//*[@class='vec-dl-ui-open-context-menu']");
	
	testUtils.findElementBy(".//*[@class='vec-dl-ui-open-context-menu']").click();
	
	waitUtils.waitUntil(".//*[@class='vec-dl-ui-file-version-context-menu']//*[contains(text(), 'Revert')]");

	testUtils.findElementBy(".//div[@class='vec-dl-ui-file-version-context-menu']/div[contains(text(),'Revert')]").click();

	waitUtils.waitSeconds(3);

	uploadedFile.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Properties')]");

	quickShareOptions.clickProperties();

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-dlg']");

	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a", "1.0"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a", "2.0"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-app-row vec-dl-ui-row0 vec-app-alt']", "3.0"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-app-row vec-dl-ui-row0 vec-app-alt']", "129.0 kB"), equalTo(true));
    }

    @Test
    public void _5_canDeleteOldVersion() {
	SecondFolderRow uploadedFile = pages.getPageBy(SecondFolderRow.class);
	uploadedFile.clickMoreOptionsIcon();

	waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Properties')]");

	QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
	quickShareOptions.clickProperties();

	waitUtils.waitUntil(".//div[@class='vec-dl-ui-file-dlg']");

	waitUtils.waitUntilClickable(".//div[@class='vec-app-row vec-dl-ui-row2 vec-app-alt']//div[@class='vec-dl-ui-open-context-menu']");
	
	testUtils.findElementBy(".//div[@class='vec-app-row vec-dl-ui-row2 vec-app-alt']//div[@class='vec-dl-ui-open-context-menu']")
		.click();

	waitUtils.waitUntil(".//*[@class='vec-dl-ui-file-version-context-menu']");

	testUtils.findElementBy(".//div[@class='vec-dl-ui-file-version-context-menu']/div[contains(text(),'Delete')]").click();

	waitUtils.waitUntilInvisibilityOfElementBy(".//div[@class='vec-app-row vec-dl-ui-row2 vec-app-alt']");

	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a", "1.0"), equalTo(false));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-dl-ui-file-dlg-versions vec-app-table']//a", "2.0"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-app-row vec-dl-ui-row0 vec-app-alt']", "3.0"), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//div[@class='vec-app-row vec-dl-ui-row0 vec-app-alt']", "129.0 kB"), equalTo(true));
    }

    @AfterMethod
    public void afterEachTest() {
	testUtils.findElementBy(".//*[@class='vec-app-buttons']/button[contains(text(),'Save')]").click();
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }

}
