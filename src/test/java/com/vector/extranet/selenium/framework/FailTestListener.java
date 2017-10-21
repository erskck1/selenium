package com.vector.extranet.selenium.framework;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class FailTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
	if(result.getMethod().getRetryAnalyzer()!=null && result.getMethod().getRetryAnalyzer().retry(result)) {

		XmlSuite suite = new XmlSuite();
		suite.setName("rerunFailedTestClasses");
		XmlTest test = new XmlTest(suite);
		test.setName(result.getTestName());
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(result.getTestClass().getXmlClass());
		test.setXmlClasses(classes);
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();
	}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
	for (ITestNGMethod method : context.getAllTestMethods()) {
	    method.setRetryAnalyzer(new RetryAnalyzer()); 
	}
    }

    @Override
    public void onFinish(ITestContext context) {
    }

}
