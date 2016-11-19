package com.lu.jack.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lu.jack.service.LoginService;
import com.lu.jack.service.ShowService;

@Controller
public class ShowController {
	
	@Resource
	private LoginService loginService;
	

	@Resource ShowService showService;
	
	
	@RequestMapping(value="/show",method=RequestMethod.GET)	
	 public String showProducts(@RequestParam int id,ModelMap modelMap,HttpServletRequest request){
		Map<String,Object> root=new HashMap<String,Object>(); 
		
		if(loginService.isLoged(request)){
		
			
			root.put("product", showService.showProduct(id));
			root.put("user", loginService.getUserMap(request));
			
		
			modelMap.addAllAttributes(root);
		 return "show";
		}
		 return "login";
	}	
}
