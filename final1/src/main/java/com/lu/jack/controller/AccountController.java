package com.lu.jack.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lu.jack.service.LoginService;
import com.lu.jack.service.ShowService;

@Controller
public class AccountController {
		
	@Resource
	LoginService loginService;
	
	@Resource
	ShowService  showService;
	
	//如果是map 则用addAllAttributes处理
	@RequestMapping(value="/account",method=RequestMethod.GET)
	public String showAccount(ModelMap modelMap ,HttpServletRequest request){
		
		if(loginService.isLoged(request)){
			modelMap.addAttribute("user",loginService.getUserMap(request));
			modelMap.addAllAttributes(showService.showPurchasedProduct());
			return "account";
		}
		
		
		return "login";
	}
	
	
	
	@RequestMapping("/api/buy")
	public ModelAndView buyProduct(@Param(value="id") int id,HttpServletRequest request){
			
		return  showService.buyProduct(id);
				
	}
}
