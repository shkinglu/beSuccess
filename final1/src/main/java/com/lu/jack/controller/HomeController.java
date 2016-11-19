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


/**
 * @author cnluja
 *
 */

@Controller

public class HomeController {
	
	@Resource
	LoginService loginService;
	
	@Resource
	ShowService  showService;
	

/**
 * @author cnluja
 * homePage
 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String indexController(HttpServletRequest request, ModelMap modelMap){
	
	
		
		modelMap.addAttribute("productList", showService.showAllProduct());
		
		if(loginService.isLoged(request)){
	
			
			
			modelMap.addAttribute("user",loginService.getUserMap(request));
			return "index";
		}
	
		return "index";
	}

/**
 * @author cnluja
 * delet by id
 */
	@RequestMapping("/api/delete")
	public ModelAndView deletePage(@Param(value="id") int id,HttpServletRequest request){
		
		return showService.deleteProduct(id);
	}
}
