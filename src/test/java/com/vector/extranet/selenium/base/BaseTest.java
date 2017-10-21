package com.vector.extranet.selenium.base;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;

import com.google.inject.Inject;
import com.vector.extranet.selenium.framework.ActivationEmailLink;
import com.vector.extranet.selenium.framework.Configuration;
import com.vector.extranet.selenium.framework.DataSourceProvider;
import com.vector.extranet.selenium.framework.Pages;
import com.vector.extranet.selenium.framework.TestListener;
import com.vector.extranet.selenium.model.UserAccount;
import com.vector.extranet.selenium.service.AccountService;
import com.vector.extranet.selenium.service.TestInfosLoggerService;
import com.vector.extranet.selenium.service.LoginService;
import com.vector.extranet.selenium.service.MemberAreaService;
import com.vector.extranet.selenium.service.WebDriverService;
import com.vector.extranet.selenium.util.TestUtil;
import com.vector.extranet.selenium.util.UserGenerator;
import com.vector.extranet.selenium.util.WaitUtils;

@Listeners(TestListener.class)
@Guice(modules = { DataSourceProvider.class })
public abstract class BaseTest {

    private final static Logger logger = LogManager.getLogger(BaseTest.class);
    
    @Inject
    protected Configuration config;

    @Inject
    protected DataSource dataSource;

    @Inject
    protected UserGenerator userGenerator;

    @Inject
    protected Pages pages;

    @Inject
    protected TestUtil testUtils;

    @Inject
    private WebDriverService webDriverService;

    @Inject
    protected ActivationEmailLink activationEmailLink;

    @Inject
    protected AccountService accountService;

    @Inject
    protected WaitUtils waitUtils;

    @Inject
    protected LoginService loginService;

    @Inject
    protected MemberAreaService memberAreaService;

    @Inject 
    private TestInfosLoggerService testInfosLoggerService;
 
    private UserAccount userOfTheTest;

    @BeforeSuite
    public void beforeSuite() {
	testInfosLoggerService.printWebDriverInfos();
    }

    @AfterSuite
    public void afterSuite() {
	testInfosLoggerService.printFailedAndSkippedTests();
    }

    @BeforeClass
    public void start() {
	logger.info(String.format("%s started", getTestClassName()));
	webDriverService.configureWebDriver(getTestClassName());
	userOfTheTest = prepareUserOfTheTest();
	testUtils.homePage();
    }

    @AfterClass
    public void finish(ITestContext context) {
	if (userOfTheTest != null && userOfTheTest.isActivated()) {
	    accountService.deleteUser(userOfTheTest);
	    userOfTheTest = null;
	}
	userOfTheTest = null;
	webDriverService.quit();
	testInfosLoggerService.addFailedTest(context, getTestClassName());
	testInfosLoggerService.addSkippedTest(context, getTestClassName());
	logger.info(String.format("%s finished", getTestClassName()));
    }

    @AfterMethod
    public void afterEachTest() {
	waitUtils.waitOneSecond();
    }

    public UserAccount prepareUserOfTheTest() {
	if (userOfTheTest == null) {
	    userOfTheTest = createTestSpecificUser();
	}
	return userOfTheTest;
    }

    protected UserAccount getUser() {
	return userOfTheTest;
    }

    protected String getTestClassName() {
	return this.getClass().getSimpleName();
    }
    
    protected void loginAs(String email, String password) {
	pages.Login().loginAs(email, password);
	waitUtils.waitForLoad();
	
	if(testUtils.containsTextUnder(".//*[@id='fm']/div", "I Agree")) {
	    testUtils.findElementBy(".//*[@id='fm']/div/button[1]").click();
	    waitUtils.waitForLoad();
	}
	
    }
    
    protected void loginAs(UserAccount user) {
	loginAs(user.getEmail(), user.getPassword());
    }

    public abstract UserAccount createTestSpecificUser();

}
