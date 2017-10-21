package com.vector.extranet.selenium.framework;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Singleton;

@Singleton
public class Configuration {

    private Properties props;

    public Configuration() throws IOException {
	this.props = new Properties();
	this.props.load(getClass().getResourceAsStream("/selenium.properties"));
    }

    public String browser() {
	return this.props.getProperty("sel.browser");
    }

    public String browserVersion() {
	return this.props.getProperty("sel.browserVersion");
    }
    
    public String operatingSystem() {
	return this.props.getProperty("sel.os");
    }
    
    public String operatingSystemVersion() {
	return this.props.getProperty("sel.osVersion");
    }
    
    public String resolution() {
	return this.props.getProperty("sel.resolution");
    }
    
    public String debug() {
	return this.props.getProperty("sel.debug");
    }
    
    public String video() {
	return this.props.getProperty("sel.video");
    }
    
    public String build() {
	return this.props.getProperty("sel.buildKey");
    }
    
    public String project() {
	return this.props.getProperty("sel.project");
    }
    
    public String getBrowserStackUser() {
	String username = System.getenv("BROWSERSTACK_USERNAME");
	return StringUtils.isEmpty(username) ? this.props.getProperty("sel.browserStackUser") : username;
    }
    
    public String getBrowserStackKey() {
	String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
	return StringUtils.isEmpty(accessKey) ? this.props.getProperty("sel.browserStackKey") : accessKey;
    }
    
    public int browserHeight() {
	return Integer.parseInt(this.props.getProperty("selenium.browserHeight"));
    }

    public int browserWidth() {
	return Integer.parseInt(this.props.getProperty("selenium.browserWidth"));
    }

    public String landingPageUrl() {
	return this.props.getProperty("selenium.landingPageUrl");
    }

    public String supportTicketForcedReindexingUrl() {
	return landingPageUrl() + "/delegate/support-tickets-reindexer/";
    }

    public String extendedMemberAreaUrl() {
	return this.props.getProperty("selenium.extendedMemberAreaUrl");
    }

    public String memberUser() {
	return this.props.getProperty("selenium.memberUser");
    }

    public String memberPassword() {
	return this.props.getProperty("selenium.memberPassword");
    }

    public String memberUser2() {
	return this.props.getProperty("selenium.memberUser2");
    }

    public String memberPassword2() {
	return this.props.getProperty("selenium.memberPassword2");
    }

    public String powerUserUser() {
	return this.props.getProperty("selenium.powerUserUser");
    }

    public String powerUserPassword() {
	return this.props.getProperty("selenium.powerUserPassword");
    }

    public String proxyUrl() {
	return this.props.getProperty("selenium.proxyUrl");
    }

    public String getDatabaseUrl() {
	return this.props.getProperty("selenium.databaseUrl");
    }

    public String getDatabaseUser() {
	return this.props.getProperty("selenium.databaseUser");
    }

    public String getDatabasePassword() {
	return this.props.getProperty("selenium.databasePassword");
    }

    public String getProxyHost() {
	return this.props.getProperty("selenium.proxyHost");
    }

    public String getProxyPort() {
	return this.props.getProperty("selenium.proxyPort");
    }

    public String getProxyUser() {
	return this.props.getProperty("selenium.proxyUser");
    }

    public String getProxyPassword() {
	return this.props.getProperty("selenium.proxyPassword");
    }

    public String getCreateAccountUrl() {
	return landingPageUrl() + props.getProperty("selenium.createAccount");
    }

    public String getUrlOf(String key) {
	return landingPageUrl() + props.getProperty(key);
    }

    public String getMessageBy(String key) {
	return props.getProperty(key);
    }
    
    public String getBrowserStackUrl() {
	String username = getBrowserStackUser();
	String accessKey = getBrowserStackKey();
	if(username.contains("bamboo")) {
	    username = username.split("-")[0];
	}
	return String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub", username, accessKey);
    }
    
    public String getBrowserStackLocal() {
	return "true";
    }
    
    public String getBrowserStackLocalIdentifier() {
   	return System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
    }
    
    
}
