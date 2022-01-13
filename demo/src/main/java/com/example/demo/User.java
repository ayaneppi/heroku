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
	private Long Id;
	private String Name;
    private String Passwords;
    private String User_name;
    
    public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		this.Id = id;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		this.User_name = user_name;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getPassWord() {
		return Passwords;
	}
	public void setPassWord(String passWord) {
		this.Passwords = passWord;
	}
	public User() {
	}
	
	public User(String name, String Passwords) {
		setName(name);
		setPassWord(Passwords);
	}
	
}
