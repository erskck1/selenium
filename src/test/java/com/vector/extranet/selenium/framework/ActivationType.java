package com.vector.extranet.selenium.framework;

public enum ActivationType {
	 ACCOUNT_ACTIVATION ( "/activation?ticketKey="), 
	 PW_RESET ("/new-password?ticketKey="); 
	 
	 private String type; 
	 
	 private ActivationType(String type) {
		 this.type = type; 
	}
	 
	 public String getType () {
		 return this.type; 
	 }
}
