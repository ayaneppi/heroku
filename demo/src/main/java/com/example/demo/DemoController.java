package com.example.demo;

import java.util.List;

import javax.swing.event.TableColumnModelListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class DemoController {
	@Autowired
    Repository rep;
	@Autowired
	UserService us;
	
	@RequestMapping("/")
	public String index(Model model) {

		model.addAttribute("iserror",false);
		return "login";
	}
	
	@RequestMapping("/login")
	public ModelAndView logincheck(
		@RequestParam("username")String userName,
		@RequestParam("password")String passWord,
		Model model) {
		ModelAndView mav = new ModelAndView();
		//ユーザ検索結果の取得
		User u = null;
		
		//loginurl直打ちのとき
		if(userName == null) {
			model.addAttribute("isnull", true);
			mav.setViewName("Login");
			return mav;
		}
		try {
			u = us.findbyName(userName);
		} catch (UsernameNotFoundException e) {
			//独自の例外です
			System.out.println(e.getMessage());
		}
		//ログインに失敗した場合
		if(u == null || !u.getPassWord().equals(passWord)) {
			model.addAttribute("iserror", true);
			mav.setViewName("Login");
			return mav;
		}else {
			List<Property> propertyList = rep.findAll();
			mav.addObject("propertyList", propertyList);
			mav.setViewName("index");
			return mav;
		}
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

