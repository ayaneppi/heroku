package com.example.demo;

public class UsernameNotFoundException extends Exception {
	private static final long serialVersionUID = 1L; 
	UsernameNotFoundException(String msg){
		super(msg);
	}
}
