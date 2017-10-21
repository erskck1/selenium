package com.vector.extranet.selenium.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.BasePage;

public class Paging extends BasePage {

    @FindBy(xpath=".//*[@id='admin-light-member-area-table_length']/label/select")
    private WebElement entries;
    
    @FindBy(xpath = "")
    private WebElement previous;
    
    @FindBy(xpath= "")
    private WebElement next;
    
    @FindBy(xpath=".//*[@id='admin-light-member-area-table_paginate']/span/a")
    private List<WebElement> pages;
    
    public Paging previous() {
	click(previous);
	return this;
    }
    
    public Paging next() {
	click(next);
	return this;
    }
    
    public Paging pages(int page) {
	click(pages.get(page));
	return this;
    }
    
    public int pagesSize() {
	if(pages == null) {
	    return 0;
	}
	return pages.size();
    }
    
    /*
     * value can be only ;
     * 10,25,50,100,-1
     * -1 means All 
     */
    public Paging entries(String value) {
	elementsUtil.selectValueFromDropdown(entries, value);
	return this;
    }
}
