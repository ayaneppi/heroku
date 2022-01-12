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
	public String heroku(
		@RequestParam("Id")Long Id,
		@RequestParam("newName")String newName,
		Model model
		) {
		Iterable<Property> findPropertyList = cr.findAll();
		String oldName = null; 
		for(Property p : findPropertyList){
			//入力されたIdと一致した場合は変更前のProperty名を取得する
			if(p.getId() == Id) {
				oldName = p.getName();
			}
		}
	  model.addAttribute("oldName",oldName);
	  model.addAttribute("Id",Id);
	  model.addAttribute("newName",newName);
      return "temp";
	}
}