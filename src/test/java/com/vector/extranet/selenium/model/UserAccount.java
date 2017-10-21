package com.vector.extranet.selenium.model;

import org.apache.commons.lang3.RandomStringUtils;

public final class UserAccount {

    private final String email;
    private String password;
    private String passwordRepeat;
    private final String lastName = RandomStringUtils.randomAlphanumeric(10);
    private final String firstName = RandomStringUtils.randomAlphanumeric(10);
    private final String country = "DE";
    private final String postCode = "72074";
    private final String city = "Stuttgart";
    private final String telefonNumber = "070712543366";
    private final String faxNumber = "070712543366";
    private final String organization = "Vector Portal";
    private final String title = "1";
    private final String degree = "4";
    private final String organizationUnit = "ADP2";
    private final String street = "Schwabstr.";
    private boolean isActivated = false;

    public UserAccount(final String newPassword, UserAccount user) {
	this.email = user.getEmail();
	this.password = newPassword;
	this.passwordRepeat = newPassword;
    }
    
    public UserAccount(final String email) {
	this.email = email;
	this.password = "1234Abc!$%DUD45";
	this.passwordRepeat = "1234Abc!$%DUD45";
    }

    public UserAccount(final String email, final String password) {
	this.email = email;
	this.password = password;
	this.passwordRepeat = password;
    }

    public void setPassword(String password) {
	this.password = password;
	this.passwordRepeat = password;
    }
    
    public String getNameAndSurname() {
	return String.format("%s %s", firstName, lastName);
    }

    public String getEmail() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    public String getPasswordRepeat() {
	return passwordRepeat;
    }

    public String getLastName() {
	return lastName;
    }

    public String getFirstName() {
	return firstName;
    }

    public String getCountry() {
	return country;
    }

    public String getPostCode() {
	return postCode;
    }

    public String getCity() {
	return city;
    }

    public String getTelefonNumber() {
	return telefonNumber;
    }

    public String getFaxNumber() {
	return faxNumber;
    }

    public String getOrganization() {
	return organization;
    }

    public String getTitle() {
	return title;
    }

    public String getDegree() {
	return degree;
    }

    public String getOrganizationUnit() {
	return organizationUnit;
    }

    public String getStreet() {
	return street;
    }

    public void setActivated(boolean status) {
	isActivated = status;
    }

    public boolean isActivated() {
	return isActivated;
    }
    
}
