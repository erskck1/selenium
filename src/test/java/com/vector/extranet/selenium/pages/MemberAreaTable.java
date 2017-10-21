package com.vector.extranet.selenium.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class MemberAreaTable extends BasePage {

    @FindBy(xpath = ".//*[@id='admin-light-member-area-table']/tbody/tr")
    private List<WebElement> memberAreas;

    public int memberAreasSize() {
	return memberAreas.size();
    }

    public WebElement getMemberArea(int index) {
	return memberAreas.get(index);
    }

    public WebElement getMemberAreaBy(String name) {
	for (WebElement memberArea : memberAreas) {
	    String memberAreaName = memberArea.findElement(By.xpath("td[1]/a")).getText();
	    if (StringUtils.equals(memberAreaName, name)) {
		return memberArea;
	    }
	}
	return null;
    }
}
