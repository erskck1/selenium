package com.vector.extranet.selenium.base;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class PageWithTitle extends BasePage {
	
	@FindBy (xpath = "//h1[@class='portlet-title']//span[@class='portlet-title-text']")
	private WebElement pageTitle;
	
	public boolean isPageCorrect() {
		try {
			return StringUtils.equals(pageTitle.getText().trim(), getPortletTitle());
		} catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public abstract String getPortletTitle();
	public abstract String getPageTitle();
}
