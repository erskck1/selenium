package com.vector.extranet.selenium.service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vector.extranet.selenium.base.BaseService;

@Singleton
public class TestInfosLoggerService extends BaseService{

    private final static Logger logger = LogManager.getLogger(TestInfosLoggerService.class);

    private Set<String> failedTests;
    private Set<String> skippedTests;
    private Set<String> failedTestClasses;
    
    @Inject
    public TestInfosLoggerService() {
	failedTests = new LinkedHashSet<String>();
	skippedTests = new LinkedHashSet<String>();
	failedTestClasses = new HashSet<String>();
    }
    
    public void addFailedTest(ITestContext context, String klazz) {
	Set<ITestResult> map =context.getFailedTests().getAllResults();
	if(map==null || map.size() ==0) {
	    return;
	}
	failedTests.add(String.format("%s", klazz));
	failedTestClasses.add(String.format("%s", klazz));
	for(ITestResult it : map) {
	    failedTests.add(String.format("%s", it.getName()));
	}
	
    }
    
    public void addSkippedTest(ITestContext context, String klazz) {
	Set<ITestResult> map =context.getSkippedTests().getAllResults();
	if(map==null || map.size() ==0) {
	    return;
	}
	skippedTests.add(String.format("%s", klazz));
	failedTestClasses.add(String.format("%s", klazz));
	for(ITestResult it : map) {
	    skippedTests.add(String.format("%s", it.getName()));
	}
    }
    
    public void printFailedAndSkippedTests() {
	if(failedTests.size() != 0) {
	    logger.error("###     FailedTests     ###"); 
	}
	for(String s : failedTests) {
	    logger.error(s);
	}
	if(skippedTests.size() != 0) {
	    logger.error("###     SkippedTests     ###"); 
	}
	for(String s : skippedTests) {
	    logger.error(s);
	}
	
	if(failedTestClasses.size() != 0) {
	   
	    StringJoiner joiner = new StringJoiner(",");
	    for(String str : failedTestClasses) {
		joiner.add(str);
	    }
	    logger.error(String.format("Failed Classes : %s",joiner.toString()));
	}
    }
    
    public void printWebDriverInfos() {
	logger.info(String.format("Webdriver is creating with params \nBrowser :%s"
		+ "\nBrowser Version : %s"
		+ "\nOS : %s"
		+ "\nOS Version : %s"
		+ "\nProject : %s"
		+ "\nBuild : %s"
		+ "\nUser Name : %s"
		+ "\nUser Key : %s"
		+ "\nBrowserStack Url : %s"
		+ "\nLocal : %s"
		+ "\nLocal Identifier : %s", config.browser(),
			config.browserVersion(),
			config.operatingSystem(),
			config.operatingSystemVersion(),
			config.project(), config.build(),
			config.getBrowserStackUser(),
			config.getBrowserStackKey(),
			config.getBrowserStackUrl(),
			config.getBrowserStackLocal(), 
			config.getBrowserStackLocalIdentifier()));
	
	logger.info(String.format("User Home : %s # User Dir : %s # tmp : %s", System.getProperty("user.home"),System.getProperty("user.dir"),System.getProperty("java.io.tmpdir")));
    }
}
