package com.vector.extranet.selenium.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vector.extranet.selenium.base.BaseService;
import com.vector.extranet.selenium.pages.AddMemberPage;
import com.vector.extranet.selenium.pages.AddMemberPopup;
import com.vector.extranet.selenium.pages.FileToolbar;
import com.vector.extranet.selenium.pages.MemberAreaAdministrationPage;
import com.vector.extranet.selenium.pages.MemberAreaAdministrationPopup;
import com.vector.extranet.selenium.pages.MemberAreaTable;
import com.vector.extranet.selenium.pages.NewFolderPopup;
import com.vector.extranet.selenium.pages.Paging;
import com.vector.extranet.selenium.pages.TabsNavigation;

public class MemberAreaService extends BaseService {
    public String createMemberArea(String testCode) {

	String memberAreaName = StringUtils.lowerCase(RandomStringUtils.randomAlphanumeric(15)) + "_BySelenium_TestName:" + testCode;
	
	waitUtils.waitUntilClickable(".//*[@id='main-menu']//a");
	
	TabsNavigation tabsNavigation = pages.getPageBy(TabsNavigation.class);
	tabsNavigation.memberAreaAdministration();

	MemberAreaAdministrationPage memberAreaAdministrationPage = pages.getPageBy(MemberAreaAdministrationPage.class);
	memberAreaAdministrationPage.createNewMemberArea();

	MemberAreaAdministrationPopup popup = pages.getPageBy(MemberAreaAdministrationPopup.class);

	popup.name(memberAreaName).expirationDate();

	popup.create();

	waitUtils.waitSeconds(3);
	
	return memberAreaName;
    }

    public WebElement findMemberAreaBy(String memberAreaName) {
	Paging pagination = pages.getPageBy(Paging.class);
	pagination.entries("50");

	waitUtils.waitUntilClickable(String.format(".//*[@id='admin-light-member-area-table']//*[contains(text(), '%s')]", memberAreaName));

	MemberAreaTable memberAreaTable = pages.getPageBy(MemberAreaTable.class);
	WebElement memberArea = memberAreaTable.getMemberAreaBy(memberAreaName);

	return memberArea;
    }

    public void addMemberToMemberArea(WebElement memberArea, String email) {
	addMemberToMemberAreaButton(memberArea);

	AddMemberPage addMemberPage = pages.getPageBy(AddMemberPage.class);
	addMemberPage.addMember();

	waitUtils.waitUntilPresenceOfElementLocatedBy("role");

	AddMemberPopup addMemberPagePopup = pages.getPageBy(AddMemberPopup.class);
	addMemberPagePopup.searchBox(email).searchButton();

	addMemberPagePopup.roleDropdown("MemberArea Uploader");

	waitUtils.waitUntil(".//*[@class='vec-app-col']/input[@type='checkbox']");

	WebElement selectUser = testUtils.findElementBy(".//*[@class='vec-app-col']/input[@type='checkbox']");
	selectUser.click();

	addMemberPagePopup.addSelectedUsers();
    }

    public void deleteMemberArea(String memberAreaName) {
	testUtils.homePage();
	
	waitUtils.waitUntilClickable("//ul[@id='main-menu']//a[@href='/web/admin-light']");
	
	TabsNavigation tabsNavigation = pages.getPageBy(TabsNavigation.class);
	tabsNavigation.memberAreaAdministration();

	waitUtils.waitForLoad();
	
	WebElement memberArea = findMemberAreaBy(memberAreaName);

	deleteMemberAreaButton(memberArea);

	waitUtils.waitOneSecond();
    }
    
    public void createNewFolder(String folderName) {
	waitUtils.waitUntilClickable(".//*[@class='vec-dl-ui-toolbar']/button[contains(text(),'New Folder')]");

	FileToolbar fileToolbar = pages.getPageBy(FileToolbar.class);
	fileToolbar.newFolder();
	
	waitUtils.waitUntil(".//*[@class='vec-app-dialog-popin-wrapper-inner']");

	NewFolderPopup newFolderPopup = pages.getPageBy(NewFolderPopup.class);
	newFolderPopup.folderNameInput(folderName);
	newFolderPopup.createButton();
	
	waitUtils.waitUntilClickable(String.format(".//vec-dl-ui-entry-list-item//*[contains(text(),'%s')]", folderName));
    }

    public void deleteMemberAreaButton(WebElement memberArea) {
	memberArea.findElement(By.xpath("td[7]/a[2]")).click();
    }

    public void addMemberToMemberAreaButton(WebElement memberArea) {
	memberArea.findElement(By.xpath("td[7]/a[3]")).click();
    }

    public void memberAreaLink(WebElement memberArea) {
	memberArea.findElement(By.xpath("td[1]/a")).click();
	waitUtils.waitForLoad();
    }
}
