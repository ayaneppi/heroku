package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository ur;
	
	//Userの検索
	public User findbyName(String Name) throws UsernameNotFoundException{
        User u = ur.findByName(Name);
        if (u == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return u;
    }
}
