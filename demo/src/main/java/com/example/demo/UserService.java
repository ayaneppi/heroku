package com.example.demo;

import javax.transaction.Transactional;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository ur;
	
	//Userの検索
	public User findbyName(String Name){
        return (User) ur.findByName(Name);
    }
}
