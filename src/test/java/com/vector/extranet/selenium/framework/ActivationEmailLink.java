package com.vector.extranet.selenium.framework;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ActivationEmailLink {

    @Inject
    private DataSource dataSource;

    @Inject
    private Configuration config;

    public String getTokenUrlForResetPassword(String email) {
	waitForTwoSeconds();
	return getTokenUrl(email, ActivationType.PW_RESET);
    }

    public String getTokenUrlForAccountActivation(String email) {
	waitForTwoSeconds();
	return getTokenUrl(email, ActivationType.ACCOUNT_ACTIVATION);
    }

    private String getTokenUrl(String mail, ActivationType type) {
	ResultSet rs;
	String ticket = "";
	try {
	    rs = dataSource.getConnection().createStatement().executeQuery("select key_ from Ticket where extraInfo like '%" + mail + "%'");
	    while(rs.next()) {
		ticket = rs.getString("key_");
	    }
	    return this.config.landingPageUrl() + type.getType() + ticket;

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    private void waitForTwoSeconds() {
	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

}
