package com.vector.extranet.selenium.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vector.extranet.selenium.framework.Configuration;
import com.vector.extranet.selenium.model.UserAccount;

@Singleton
public class UserGenerator {

    @Inject
    private Configuration configuration;

    private UserAccount activeSystemUser;

    private UserAccount secondActiveSystemUser;

    private UserAccount adminUser;

    public UserAccount createNewUser() {
	return new UserAccount(TestUtil.createRandomEmail());
    }

    public UserAccount getActiveUser() {
	if (activeSystemUser == null) {
	    activeSystemUser = getFirstActiveUser();
	}
	return activeSystemUser;
    }

    public UserAccount getAnotherActiveUser() {
	if (secondActiveSystemUser == null) {
	    secondActiveSystemUser = getSecondActiveUser();
	}
	return secondActiveSystemUser;
    }

    public UserAccount getAdminUser() {
	if (adminUser == null) {
	    adminUser = prepareAdminUser();
	}
	return adminUser;
    }

    private UserAccount getSecondActiveUser() {
	String email = configuration.memberUser2();
	String password = configuration.memberPassword2();
	return new UserAccount(email, password);
    }

    private UserAccount getFirstActiveUser() {
	String email = configuration.memberUser();
	String password = configuration.memberPassword();
	return new UserAccount(email, password);
    }

    private UserAccount prepareAdminUser() {
	String email = configuration.powerUserUser();
	String password = configuration.powerUserPassword();
	return new UserAccount(email, password);
    }
}
