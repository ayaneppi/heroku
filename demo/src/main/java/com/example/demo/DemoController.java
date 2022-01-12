package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import省略

@Controller
public class DemoController {

	@Autowired Repository cr;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		Iterable<Property> propertyList = cr.findAll();
		mav.addObject("propertyList", propertyList);
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/heroku")
	public ModelAndView heroku(
		@RequestParam("Id")Long Id,
		@RequestParam("newName")String newName,
		Model model
		) {
		ModelAndView mav = new ModelAndView();
		Iterable<Property> findPropertyList = cr.findAll();
		List<Object> propertyList = new ArrayList<Object>();
		for(Property p : findPropertyList){
			//入力されたIdと一致した場合は変更前のProperty名を取得する
			if(p.getId() == Id) {
				propertyList.add(Id);
				propertyList.add(p.getName());
				propertyList.add(newName);
				System.out.println(propertyList);
			}
		}
		mav.addObject("propertyList", propertyList);
		mav.setViewName("temp");
      return mav;
	}
}