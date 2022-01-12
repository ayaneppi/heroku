package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Property__c",schema = "salesforce")
public class Property {
	
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Long id;
	    private String Name;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
	    
	    

}
