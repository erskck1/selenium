package com.vector.extranet.selenium.framework;

import org.openqa.selenium.support.PageFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vector.extranet.selenium.base.BasePage;
import com.vector.extranet.selenium.pages.CreateAccountPage;
import com.vector.extranet.selenium.pages.InfoPage;
import com.vector.extranet.selenium.pages.LoginPage;
import com.vector.extranet.selenium.pages.ResetPasswordPage;
import com.vector.extranet.selenium.pages.ResetPasswordRequestPage;
import com.vector.extranet.selenium.pages.TopNavigationPage;
import com.vector.extranet.selenium.service.WebDriverService;
import com.vector.extranet.selenium.util.ElementsUtil;

@Singleton
public class Pages {

    @Inject
    private WebDriverService webDriverService;

    @Inject
    private ElementsUtil elementsUtil;

    private <T> T initializePage(Class<T> klazz) {
	try {
	    T page = klazz.newInstance();
	    PageFactory.initElements(webDriverService.getWebDriver(), page);
	    return page;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public LoginPage Login() {
	return getPageBy(LoginPage.class);
    }

    public TopNavigationPage TopNavigation() {
	return getPageBy(TopNavigationPage.class);
    }

    public CreateAccountPage CreateAccount() {
	return getPageBy(CreateAccountPage.class);
    }

    public ResetPasswordRequestPage ResetPasswordRequest() {
	return getPageBy(ResetPasswordRequestPage.class);
    }

    public ResetPasswordPage ResetPassword() {
	return getPageBy(ResetPasswordPage.class);
    }

    public InfoPage Info() {
	return getPageBy(InfoPage.class);
    }

    public <T extends BasePage> T getPageBy(Class<T> klazz) {
	T initializedPage = initializePage(klazz);
	initializedPage.setElementsUtil(elementsUtil);
	return initializedPage;
    }
}
