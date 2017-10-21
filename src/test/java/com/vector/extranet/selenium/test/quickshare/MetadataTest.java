package com.vector.extranet.selenium.test.quickshare;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.vector.extranet.selenium.base.BaseTest;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.pages.FileToolbar;
import com.vector.extranet.selenium.pages.FirstFolderRow;
import com.vector.extranet.selenium.pages.MemberAreaTabs;
import com.vector.extranet.selenium.pages.NewMetadataFirstRow;
import com.vector.extranet.selenium.pages.NewMetadataSecondRow;
import com.vector.extranet.selenium.pages.QuickShareOptions;
import com.vector.extranet.selenium.pages.ShareLinkPopup;
import com.vector.extranet.selenium.service.MemberAreaService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MetadataTest extends BaseTest {

    @Inject
    private MemberAreaService memberAreaService;

    private String firstMetadataKey;
    private String firstMetadataName;
    private String secondMetadataKey;
    private String secondMetadataName;
    private String folderName;
    private String memberAreaName;

    @BeforeClass
    public void beforeAllTests(ITestContext context) {
	loginService.toLoginPage(userGenerator.getAdminUser());
	memberAreaName = memberAreaService.createMemberArea(this.getTestClassName());
	firstMetadataKey = RandomStringUtils.randomAlphabetic(15) + "bySelenium";
	firstMetadataName = RandomStringUtils.randomAlphabetic(10);
	secondMetadataKey = RandomStringUtils.randomAlphabetic(15) + "bySelenium";
	secondMetadataName = RandomStringUtils.randomAlphabetic(10);
	folderName = String.format("%s_%s", "AAA", RandomStringUtils.randomAlphabetic(20));
    }

    @AfterClass
    public void afterTests() {
	memberAreaService.deleteMemberArea(memberAreaName);
    }

    @Test
    public void _1_isCorrectPage() {
	waitUtils.waitUntilInvisibilityOfElementBy(".//*[@id='member-area-form']");

	WebElement memberArea = memberAreaService.findMemberAreaBy(memberAreaName);
	memberAreaService.memberAreaLink(memberArea);

	waitUtils.waitForLoad();

	MemberAreaTabs memberAreaTabs = pages.getPageBy(MemberAreaTabs.class);
	memberAreaTabs.metadataTab();

	waitUtils.waitForLoad();

	assertThat(testUtils.getPageTitle(), equalTo("Metadata Administration - Customer Portal"));
    }

    @Test
    public void _2_canAddNewMetadata() {
	WebElement addMetaDataButton = testUtils
		.findElementBy(".//*[@class='vec-dl-metadata-admin-ui']//button[contains(text(),'Add Field')]");
	addMetaDataButton.click();

	waitUtils.waitUntil(".//*[@id='key%new-field0']");

	NewMetadataFirstRow newMetadata = pages.getPageBy(NewMetadataFirstRow.class);

	newMetadata.keyInput(firstMetadataKey);
	newMetadata.nameInput(firstMetadataName);
	newMetadata.type("number");
	newMetadata.showInMemberArea();
	newMetadata.showInSharedFolder();
	newMetadata.saveButton();

	assertThat(testUtils.containsTextUnder(".//*[@class='vec-app-dialog-content']", "Your request processed successfully."),
		equalTo(true));
	assertThat(testUtils.findElementBy(String.format(".//*[@id='key%s']", firstMetadataKey.toLowerCase())).isEnabled(), equalTo(false));
    }

    @Test
    public void _3_canAddNewMetadataWithSameKey() {
	WebElement addMetaDataButton = testUtils
		.findElementBy(".//*[@class='vec-dl-metadata-admin-ui']//button[contains(text(),'Add Field')]");
	addMetaDataButton.click();

	waitUtils.waitUntil(".//vec-dl-metadata-admin-ui-list-item[2]");

	NewMetadataSecondRow newMetadataSecond = pages.getPageBy(NewMetadataSecondRow.class);

	newMetadataSecond.keyInput(firstMetadataKey);
	newMetadataSecond.saveButton();

	waitUtils.waitOneSecond();

	assertThat(testUtils.containsTextUnder(".//*[@class='vec-app-dialog-content']", "Your request processed successfully."),
		equalTo(false));
	assertThat(testUtils.findElementBy(".//*[@id='key%new-field1']").isEnabled(),
		equalTo(true));

	newMetadataSecond.keyInput(RandomStringUtils.randomAlphabetic(10));
	newMetadataSecond.nameInput(firstMetadataName);
	newMetadataSecond.saveButton();

	assertThat(testUtils.containsTextUnder(".//*[@class='vec-app-dialog-content']", "Your request processed successfully."),
		equalTo(false));
	assertThat(testUtils.findElementBy(".//*[@id='key%new-field1']").isEnabled(),
		equalTo(true));
    }

    @Test
    public void _4_isShowedRequiredMessage() {
	NewMetadataSecondRow newMetadataSecond = pages.getPageBy(NewMetadataSecondRow.class);

	newMetadataSecond.keyInput("");
	newMetadataSecond.nameInput("");
	newMetadataSecond.saveButton();

	assertThat(testUtils.containsTextUnder(".//*[@class='vec-app-dialog-content']", "Your request processed successfully."),
		equalTo(false));
	assertThat(testUtils.findElementBy(".//*[@id='key%new-field1']").isEnabled(),
		equalTo(true));
    }

    @Test
    public void _5_add2ndMetadataWithoutCheckboxsChecked() {
	NewMetadataSecondRow newMetadataSecond = pages.getPageBy(NewMetadataSecondRow.class);

	newMetadataSecond.keyInput(secondMetadataKey);
	newMetadataSecond.nameInput(secondMetadataName);
	newMetadataSecond.type("string");
	newMetadataSecond.saveButton();

	assertThat(testUtils.containsTextUnder(".//*[@class='vec-app-dialog-content']", "Your request processed successfully."),
		equalTo(true));
	assertThat(testUtils.findElementBy(String.format(".//*[@id='key%s']", secondMetadataKey.toLowerCase())).isEnabled(),
		equalTo(false));
    }

    @Test
    public void _6_canShowedTheMetadataInMemberArea() {
	MemberAreaTabs memberAreaTabs = pages.getPageBy(MemberAreaTabs.class);
	memberAreaTabs.filesTab();

	waitUtils.waitUntil(".//*[@class='vec-dl-ui-entry-list']");

	assertThat(testUtils.containsTextUnder(".//*[@class='vec-dl-ui-entry-list']", firstMetadataName), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//*[@class='vec-dl-ui-entry-list']", secondMetadataName), equalTo(false));
    }

    @Test
    public void _7_canShowedTheMetadataInSharedLink() {
	memberAreaService.createNewFolder(folderName);

	FirstFolderRow createdFolder = pages.getPageBy(FirstFolderRow.class);
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

	testUtils.getPageByUrl(shareUrl);

	assertThat(testUtils.findElementBy(".//h2[@class='shared-folder-name']").getText().trim(), equalTo(folderName));
	assertThat(testUtils.containsTextUnder(".//*[@id='fileList']", firstMetadataName), equalTo(true));
	assertThat(testUtils.containsTextUnder(".//*[@id='fileList']", secondMetadataName), equalTo(false));
    }

    @Test
    public void _8_canEditFieldValueOnAFile() {
        testUtils.back();
    
        waitUtils.waitUntil(String.format(".//div[@class='vec-dl-ui-entry-list']//*[contains(text(),'%s')]", folderName));
    
        FirstFolderRow createdFolder = pages.getPageBy(FirstFolderRow.class);
        createdFolder.clickFolderName();
    
        waitUtils.waitUntil(".//div[@class='vec-dl-ui-segment vec-dl-ui-folder ng-scope']");
    
        testUtils.setFileDetector();
    
        FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
        fileToolbar.uploadFile("./resources/Ferrari.jpg");
    
        waitUtils.waitUntilInvisibilityOfElementBy(".//*[@class='vec-dl-ui-file-name ng-binding']");
    
        FirstFolderRow uploadedFile = pages.getPageBy(FirstFolderRow.class);
        uploadedFile.clickMoreOptionsIcon();
    
        waitUtils.waitUntil(".//*[@class='ng-scope']//*[contains(text(),'Properties')]");
    
        QuickShareOptions quickShareOptions = pages.getPageBy(QuickShareOptions.class);
        quickShareOptions.clickProperties();
    
        waitUtils.waitUntil(String.format(".//*[@class='vec-dl-ui-file-dlg-top-left']//*[contains(text(), '%s')]", firstMetadataName));
    
        String firstFieldInput = RandomStringUtils.randomNumeric(8);
        String secondFieldInput = RandomStringUtils.randomAlphabetic(15);
    
        testUtils.findElementBy(String.format(".//*[@id='%s']", firstMetadataKey.toLowerCase())).sendKeys(firstFieldInput);
        testUtils.findElementBy(String.format(".//*[@id='%s']", secondMetadataKey.toLowerCase())).sendKeys(secondFieldInput);
        testUtils.findElementBy(".//*[@class='vec-app-buttons']/button[contains(text(),'Save')]").click();
        
        assertThat(testUtils.containsTextUnder(".//*[@class='vec-dl-ui-entry-list']", firstFieldInput), equalTo(true));
        assertThat(testUtils.containsTextUnder(".//*[@class='vec-dl-ui-entry-list']", secondFieldInput), equalTo(false));
    }

    @Test
    public void _9_canRemoveNewMetadata() {
	MemberAreaTabs memberAreaTabs = pages.getPageBy(MemberAreaTabs.class);
	memberAreaTabs.metadataTab();

	waitUtils.waitForLoad();

	NewMetadataFirstRow newMetadataFirst = pages.getPageBy(NewMetadataFirstRow.class);
	NewMetadataSecondRow newMetadataSecond = pages.getPageBy(NewMetadataSecondRow.class);

	newMetadataSecond.removeButton();
	newMetadataFirst.removeButton();

	waitUtils.waitOneSecond();

	assertThat(testUtils.isElementExistByXpath(".//div[@class='vec-app-col']"), equalTo(false));
    }

    @Override
    public UserAccount createTestSpecificUser() {
	return userGenerator.getAdminUser();
    }

}
