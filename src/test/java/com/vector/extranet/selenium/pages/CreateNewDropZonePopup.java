package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class CreateNewDropZonePopup extends BasePage {
    
    @FindBy(xpath = ".//*[@class='vec-dl-ui-drop-zone-dlg']/form/div[1]/div")
    private WebElement dropzoneUrl;

    @FindBy(xpath = ".//*[@class='vec-app-input'][3]/div/input")
    private WebElement folderTitle;

    @FindBy(id = "expirationDate")
    private WebElement expirationDate;

    @FindBy(id="description")
    private WebElement description;

    @FindBy(id="numberOfAllowedUploads")
    private WebElement numberOfAllowedUploads;

    @FindBy(xpath = ".//input[@name='unlimitedUploads']")
    private WebElement unlimitedUploadsCheckbox;
    
    @FindBy(id =".//*[@id='allowedUploadsAtOnce']")
    private WebElement allowedUploadsAtOnce;
    
    @FindBy(id="uploadNotificationEmails")
    private WebElement uploadNotificationEmails;
    
    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Create')]")
    private WebElement createButton;

    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Update')]")
    private WebElement updateButton;

    @FindBy(xpath = ".//*[@class='vec-app-buttons']/button[contains(text(),'Remove Drop Zone')]")
    private WebElement removeDropZoneButton;

    public String getDropzoneUrlText() {
	return dropzoneUrl.getText();
    }

    public CreateNewDropZonePopup clickStopSharing() {
	click(removeDropZoneButton);
	return this;
    }

    public CreateNewDropZonePopup clickUpdate() {
	click(updateButton);
	return this;
    }

    public CreateNewDropZonePopup clickCreate() {
	click(createButton);
	return this;
    }
}
