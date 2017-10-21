package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.PageWithTitle;

public class DeleteAccountPage extends PageWithTitle {

    private final static String PORTLET_TITLE = "Delete Account";
    private final static String PAGE_TITLE = "Delete Account";

    @FindBy(xpath = "//*[@class='form-submit-button']/button")
    private WebElement delete;

    @Override
    public String getPortletTitle() {
	return PORTLET_TITLE;
    }

    @Override
    public String getPageTitle() {
	return PAGE_TITLE;
    }

    public void delete() {
	click(delete);
    }

}
