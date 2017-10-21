package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class MemberAreaAdministrationPopup extends BasePage {

    @FindBy(id = "member-area-name")
    private WebElement name;

    @FindBy(id = "member-area-expiration-date")
    private WebElement expirationDate;

    @FindBy(id = "member-area-delete-unmodified-files-after")
    private WebElement deleteUnmodified;

    @FindBy(id = "member-area-delete-empty-folder-after")
    private WebElement deleteEmptyFolder;

    @FindBy(id = "member-area-document-library-quota-gb")
    private WebElement quota;

    @FindBy(id = "member-area-drop-zone-default-expiration")
    private WebElement dropZoneExpiration;

    @FindBy(id = "member-area-shared-link-default-expiration")
    private WebElement sharedLinkExpiration;

    @FindBy(id = "member-area-shared-link-default-downloads-limit")
    private WebElement sharedLinkDownloadsLimit;

    @FindBy(id = "member-area-drop-zone-default-max-uploads")
    private WebElement dropZoneMaxUpload;

    @FindBy(id = "member-area-default-language")
    private WebElement language;

    @FindBy(id = "member-area-company")
    private WebElement company;

    @FindBy(id = "member-area-never-expire")
    private WebElement expirationDateNeverCheckbox;

    @FindBy(id = "member-area-never-delete-unmodified-files")
    private WebElement deleteUnmodifiedNeverCheckbox;

    @FindBy(id = "member-area-no-share-download-limit")
    private WebElement unlimitedCheckbox;

    @FindBy(id = "member-area-never-delete-empty-folder")
    private WebElement deleteEmptyFolderNeverCheckbox;

    @FindBy(xpath = ".//*[@class='ui-dialog-buttonset']/button[1]")
    private WebElement create;

    @FindBy(xpath = ".//*[@class='ui-dialog-buttonset']/button[2]")
    private WebElement cancel;

    public void create() {
	click(create);
    }

    public void cancel() {
	click(cancel);
    }

    public MemberAreaAdministrationPopup expirationDateNever() {
	click(expirationDateNeverCheckbox);

	if (!isExpirationDateNeverChecked()) {
	    elementsUtil.waitUntilElementClickable(expirationDate);
	}

	return this;
    }

    public MemberAreaAdministrationPopup deleteEmptyNever() {
	click(deleteEmptyFolderNeverCheckbox);

	if (!isDeleteEmptyFolderNeverChecked()) {
	    elementsUtil.waitUntilElementClickable(deleteEmptyFolder);
	}
	
	return this;
    }

    public MemberAreaAdministrationPopup deleteUnmodifiedNever() {
	click(deleteUnmodifiedNeverCheckbox);

	if (!isDeleteUnmodifiedNeverChecked()) {
	    elementsUtil.waitUntilElementClickable(deleteUnmodified);
	}

	return this;
    }

    public MemberAreaAdministrationPopup unlimited() {
	click(unlimitedCheckbox);

	if (!isUnlimitedChecked()) {
	    elementsUtil.waitUntilElementClickable(sharedLinkDownloadsLimit);
	}

	return this;
    }

    public MemberAreaAdministrationPopup language(String value) {
	elementsUtil.selectValueFromDropdown(language, value);
	return this;
    }

    public boolean isUnlimitedChecked() {
	return unlimitedCheckbox.isSelected();
    }

    public boolean isExpirationDateNeverChecked() {
	return expirationDateNeverCheckbox.isSelected();
    }

    public boolean isDeleteEmptyFolderNeverChecked() {
	return deleteEmptyFolderNeverCheckbox.isSelected();
    }
    
    public boolean isDeleteUnmodifiedNeverChecked() {
	return deleteUnmodifiedNeverCheckbox.isSelected();
    }

    public boolean isExpirationDateEnable() {
	return expirationDate.isEnabled();
    }

    public boolean isDeleteUnmodifiedEnable() {
	return deleteUnmodified.isEnabled();
    }

    public boolean isSharedLinkDownloadsLimitEnable() {
	return sharedLinkDownloadsLimit.isEnabled();
    }

    public MemberAreaAdministrationPopup expirationDate() {
	sendKeys(expirationDate, "12.12.2099");
	return this;
    }

    public MemberAreaAdministrationPopup name(String name) {
	sendKeys(this.name, name);
	return this;
    }

    public MemberAreaAdministrationPopup deleteUnmodified(String after) {
	sendKeys(this.deleteUnmodified, after);
	return this;
    }

    public MemberAreaAdministrationPopup deleteEmptyFolder(String after) {
	sendKeys(this.deleteEmptyFolder, after);
	return this;
    }

}
