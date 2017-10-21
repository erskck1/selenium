package com.vector.extranet.selenium.service;

import org.openqa.selenium.WebDriver;

import com.vector.extranet.selenium.base.BaseService;

public class WebDriverService extends BaseService {

    public void configureWebDriver(String testClassName) {
	if (getWebDriver() != null) {
	    quit();
	}
	webDriverProvider.prepareWebDriver(testClassName);
    }

    public WebDriver getWebDriver() {
	return webDriverProvider.getWebDriver();
    }
    
    public void quit() {
	webDriverProvider.quitWebDriver();
    }
}
