package com.vector.extranet.selenium.framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class WebDriverProvider {
    
    @Inject
    private Configuration config;

    private static final long TIMEOUT = 20;

    private WebDriver webDriver;

    public WebDriver getWebDriver() {
	return webDriver;
    }

    public void prepareWebDriver(String testClassName) {
	webDriver = initialize(testClassName);
	webDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	webDriver.manage().timeouts().pageLoadTimeout(TIMEOUT, TimeUnit.SECONDS);
	webDriver.manage().window().setSize(new Dimension(config.browserWidth(), config.browserHeight()));
    }

    public void quitWebDriver() {
	getWebDriver().quit();
	webDriver = null;
    }

    private WebDriver initialize(String testClassName) {
	WebDriver driver = null;
	String browserStackUrl = config.getBrowserStackUrl();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("browser", config.browser());
	capabilities.setCapability("browser_version", config.browserVersion());
	capabilities.setCapability("os", config.operatingSystem());
	capabilities.setCapability("os_version", config.operatingSystemVersion());
	capabilities.setCapability("resolution", config.resolution());
	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	capabilities.setCapability("browserstack.local",config.getBrowserStackLocal());
	capabilities.setCapability("browserstack.localIdentifier", config.getBrowserStackLocalIdentifier());
	capabilities.setCapability("browserstack.debug", config.debug());
	capabilities.setCapability("name", testClassName);
	capabilities.setCapability("browserstack.video", config.video());
	capabilities.setCapability("project", config.project());
	capabilities.setCapability("build", config.build() + " " + dtf.format(localDate));
	
	System.getProperties().put("https.proxyHost", "localhost");
	System.getProperties().put("https.proxyPort", "3128");
	
	try {
	    driver = new RemoteWebDriver(new URL(browserStackUrl), capabilities);
	} catch (MalformedURLException e1) {
	    e1.printStackTrace();
	}

	return driver;
    }

}
