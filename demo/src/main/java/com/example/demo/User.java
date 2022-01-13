package com.example.demo;

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
    public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	private String Name;
    private String passWord;
    private String user_name;
    
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public User() {
	}
	
	public User(String name, String passWord) {
		setName(name);
		setPassWord(passWord);
	}
	
}
