package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
//検索処理、レコード件数のカウント処理をかくクラス
//コントローラでここの処理を呼ぶ
public class propertyService {
	 @Autowired
	    Repository repository;

	    public Property prop(Property property) {
	        return repository.save(property);
	    }
	    /*public Property findbyName(Property property) {
	        return repository.save(property);
	    }*/
	    public List<Property> getProperty() {
	        return repository.findAll();
	    }
	    public Property updateProperty(Property property) {
	        return repository.save(property);
	    }
}
