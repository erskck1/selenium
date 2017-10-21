package com.vector.extranet.selenium.pages;

public class ModifyAccountPage extends AccountPage {

    private final static String PORTLET_TITLE = "Modify my profile";
    private final static String PAGE_TITLE = "Modify Account";

    @Override
    public String getPortletTitle() {
	return PORTLET_TITLE;
    }

    @Override
    public String getPageTitle() {
	return PAGE_TITLE;
    }
}
