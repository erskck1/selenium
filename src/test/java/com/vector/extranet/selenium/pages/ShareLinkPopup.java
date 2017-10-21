package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class ShareLinkPopup extends BasePage {

    @FindBy(xpath = ".//*[@class='vec-dl-ui-share-folder-dlg']/form/div[1]/div")
    private WebElement shareUrl;

    @FindBy(xpath = ".//*[@class='vec-app-input'][2]/div/input")
    private WebElement folderTitle;

    @FindBy(id = "expirationDate")
    private WebElement expirationDate;

    @FindBy(id = "sendLinkTo")
    private WebElement sendLinkTo;

    @FindBy(xpath = ".//*[@class='vec-app-input'][5]/div")
    private WebElement password;

    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Create')]")
    private WebElement createButton;

    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Update')]")
    private WebElement updateButton;

    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Stop Sharing')]")
    private WebElement stopSharingButton;

    public String getShareUrlText() {
	return shareUrl.getText();
    }

    public ShareLinkPopup clickStopSharing() {
	click(stopSharingButton);
	return this;
    }

    public ShareLinkPopup clickUpdate() {
	click(updateButton);
	return this;
    }

    public ShareLinkPopup clickCreate() {
	click(createButton);
	return this;
    }
}
