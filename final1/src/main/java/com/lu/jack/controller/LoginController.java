package com.lu.jack.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lu.jack.service.LoginService;


@Controller
public class LoginController {

	
	@Resource
	LoginService loginService;
	
	/**
	 * @author cnluja
	 *  check login
	 */
	@RequestMapping(value="/api/login" ,method=RequestMethod.POST)
	public ModelAndView checkLogin(@Param(value="userName") String userName ,@Param(value="password") 
	 String password,HttpServletRequest request	,ModelAndView modelView 	){
	Map<String, Object> modelMap = new HashMap<String, Object>();
		if (loginService.isExit(userName)) { // check username Exit
			if (loginService.vaildUserName(userName, password)) {   // check password right
				modelMap = loginService.getReturnMap("success");
				loginService.addSession( request ,userName, password);
				modelView.addAllObjects(modelMap);
				return modelView;
			}

			modelMap = loginService.getReturnMap("wrongpsd");
			modelView.addAllObjects(modelMap);
			return modelView;

		}
		modelMap = loginService.getReturnMap("wronguser");
		modelView.addAllObjects(modelMap);
		return modelView;

	}
	
	
	/**
	 * @author cnluja
	 *  login page 
	 */
	@RequestMapping(value="/login" )
	public String login(HttpServletRequest request, ModelMap modelMap){
		Map<String,Object> root=new HashMap<String,Object>();

		if(loginService.isLoged(request)){  //check session 
			
			root.put("user", loginService.getUserMap(request)); // get useMap
			modelMap.addAllAttributes(root);
			return "index";
		}
		
			return "login";
	}
	
	/**
	 * @author cnluja
	 *  login out
	 */
	@RequestMapping(value="/logout" ,method=RequestMethod.GET )
	public String logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.invalidate();
		
		return "login";
	}
	
}
