package com.example.demo;

import java.util.List;

import javax.swing.event.TableColumnModelListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class DemoController {
	@Autowired
    Repository rep;
	
	
	@RequestMapping(value = "/",method= RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("iserror",false);
		return "Login";
	}
	
	@RequestMapping(value = "/index",method= RequestMethod.POST)
	public ModelAndView index(
		Model model) {
		ModelAndView mav = new ModelAndView();
		
		List<Property> propertyList = rep.findAll();
		mav.addObject("propertyList", propertyList);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value ="/login-error",method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("iserror",true);
		return "Login";
	}
	
	@RequestMapping("/heroku")
	public String heroku(
		@RequestParam("Id")Long Id,
		@RequestParam("newName")String newName,
		Model model) {
		//入力値がNULLだった場合
		if(Id == null || newName == null) {
			model.addAttribute("iserror", true);
			List<Property> propertyList = rep.findAll();
			model.addAttribute("propertyList",propertyList);
			return "index";
		}else {
			String oldName = null;
			//Idで検索かける
			Property proId = rep.findById(Id).orElseThrow();
			
			//返却地がNULLだったとき
			if(proId == null) {
				model.addAttribute("iserror", true);
				List<Property> propertyList = rep.findAll();
				model.addAttribute("propertyList",propertyList);
				return "index";
			}else {
				oldName = proId.getName();
				//DBに更新かける
				proId.setName(newName);
				rep.save(proId);
				model.addAttribute("oldName",oldName);
				model.addAttribute("Id",Id);
				model.addAttribute("newName",newName);
				return "temp";
			}
		}
	}
}

