package com.vector.extranet.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vector.extranet.selenium.base.PageWithTitle;
import com.vector.extranet.selenium.model.UserAccount;

public abstract class AccountPage extends PageWithTitle {
    public static final String requiredFieldsMessage = "Please fill out all required fields. Fields with bold titles are required.";
    public static final String invalidEmailMessage = "The entered email address seems to be invalid. Please check the address.";

    @FindBy(xpath = "//button[@type = 'submit']")
    protected WebElement save;

    @FindBy(id = "email")
    protected WebElement email;

    @FindBy(id = "lastName")
    protected WebElement lastName;

    @FindBy(id = "firstName")
    protected WebElement firstName;

    @FindBy(id = "country")
    protected WebElement country;

    @FindBy(id = "zip")
    protected WebElement postcode;

    @FindBy(id = "location")
    protected WebElement city;

    @FindBy(id = "phoneNumber")
    protected WebElement telefonNumber;

    @FindBy(id = "facsimile_telephone_number")
    protected WebElement faxNumber;

    @FindBy(id = "organization")
    protected WebElement organization;

    @FindBy(id = "state")
    protected WebElement state;

    @FindBy(id = "business")
    protected WebElement business;

    @FindBy(id = "title")
    protected WebElement title;

    @FindBy(id = "degree")
    protected WebElement degree;

    @FindBy(id = "organizational_unit")
    protected WebElement organizationalUnit;

    @FindBy(id = "street")
    protected WebElement street;

    public void save() {
	click(save);
    }

    public AccountPage email(String email) {
	sendKeys(this.email, email);
	return this;
    }

    public AccountPage lastName(String lastName) {
	sendKeys(this.lastName, lastName);
	return this;
    }

    public AccountPage firstName(String firstName) {
	sendKeys(this.firstName, firstName);
	return this;
    }

    public AccountPage country(String country) {
	elementsUtil.selectValueFromDropdown(this.country, country);
	return this;
    }

    public AccountPage title(String title) {
	elementsUtil.selectValueFromDropdown(this.title, title);
	return this;
    }

    public AccountPage degree(String degree) {
	elementsUtil.selectValueFromDropdown(this.degree, degree);
	return this;
    }

    public AccountPage postcode(String postcode) {
	sendKeys(this.postcode, postcode);
	return this;
    }

    public AccountPage city(String city) {
	sendKeys(this.city, city);
	return this;
    }

    public AccountPage telefonNumber(String telefonNumber) {
	sendKeys(this.telefonNumber, telefonNumber);
	return this;
    }

    public AccountPage faxNumber(String faxNumber) {
	sendKeys(this.faxNumber, faxNumber);
	return this;
    }

    public AccountPage organization(String organization) {
	sendKeys(this.organization, organization);
	return this;
    }

    public AccountPage state(String state) {
	sendKeys(this.state, state);
	return this;
    }

    public AccountPage business() {
	click(business);
	return this;
    }

    public AccountPage fillInputFieldBy(UserAccount user) {
	sendKeys(this.email, user.getEmail());
	sendKeys(this.lastName, user.getLastName());
	sendKeys(this.firstName, user.getFirstName());
	sendKeys(this.postcode, user.getPostCode());
	sendKeys(this.city, user.getCity());
	sendKeys(this.telefonNumber, user.getTelefonNumber());
	sendKeys(this.faxNumber, user.getFaxNumber());
	sendKeys(this.organization, user.getOrganization());
	sendKeys(this.organizationalUnit, user.getOrganizationUnit());
	sendKeys(this.street, user.getStreet());
	elementsUtil.selectValueFromDropdown(this.degree, user.getDegree());
	elementsUtil.selectValueFromDropdown(this.title, user.getTitle());
	elementsUtil.selectValueFromDropdown(this.country, user.getCountry());
	return this;
    }

    public boolean isBusinessAddressChecked() {
	return business.isSelected();
    }

    public boolean isCompanyExist() {
	return elementsUtil.isElementExist(organization) && organization.isDisplayed();
    }

    public boolean isDivisionExist() {
	return elementsUtil.isElementExist(organizationalUnit) && organizationalUnit.isDisplayed();
    }

    public String getRequiredFieldsWarningMessage() {
	return requiredFieldsMessage;
    }

    public String getEmail() {
	return email.getAttribute("value");
    }

    public String getLastName() {
	return lastName.getAttribute("value");
    }

    public String getFirstName() {
	return firstName.getAttribute("value");
    }

    public String getCountry() {
	return elementsUtil.getSelectedOptionFromDropdown(country).getAttribute("value");
    }

    public String getPostcode() {
	return postcode.getAttribute("value");
    }

    public String getCity() {
	return city.getAttribute("value");
    }

    public String getTelefonNumber() {
	return telefonNumber.getAttribute("value");
    }

    public String getFaxNumber() {
	return faxNumber.getAttribute("value");
    }

    public String getOrganization() {
	return organization.getAttribute("value");
    }

    public String getState() {
	return state.getAttribute("value");
    }

    public String getTitle() {
	return elementsUtil.getSelectedOptionFromDropdown(title).getText();
    }

    public String getDegree() {
	return elementsUtil.getSelectedOptionFromDropdown(degree).getText();
    }

    public String getOrganizationalUnit() {
	return organizationalUnit.getAttribute("value");
    }

    public String getStreet() {
	return street.getAttribute("value");
    }

}
