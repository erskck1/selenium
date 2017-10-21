package com.vector.extranet.selenium.service;

import com.vector.extranet.selenium.base.BaseService;
import com.vector.extranet.selenium.model.UserAccount;

public class LoginService extends BaseService {

    public void toLoginPage(UserAccount user) {
	testUtils.defaultConfigurations();

	pages.TopNavigation().login();

	waitUtils.waitForLoad();

	pages.Login().loginAs(user);
	
	waitUtils.waitSeconds(3);
	
	if(testUtils.containsTextUnder(".//*[@id='fm']/div", "I Agree")) {
	    testUtils.findElementBy(".//*[@id='fm']/div/button[1]").click();
	    waitUtils.waitForLoad();
	}
    }
}
