package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User__c",schema = "salesforce")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private boolean admin__c;
	private String name;
    private String passwords__c;
    private String user_name__c;
    private Boolean enabled__c;
    
    
	public void setAdmin(boolean admin) {
		this.admin__c = admin;
	}
	public boolean isAdmin() {
		return admin__c;
	}
    public Boolean getEnabled() {
		return enabled__c;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled__c = enabled;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name__c;
	}
	public void setUser_name(String user_name) {
		this.user_name__c = user_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passwords__c;
	}
	public void setPassWord(String passwords__c) {
		this.passwords__c = passwords__c;
	}
	public User() {}
	
	public User(String name,String passwords__c,Boolean enabled) {
		setName(name);
		setPassWord(passwords__c);
		setEnabled(enabled);
	}
	public boolean isEnabled() {
		return enabled__c;
	}
}
