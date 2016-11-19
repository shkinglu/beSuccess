package com.lu.jack.serviceimp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.lu.jack.dao.UserMapper;
import com.lu.jack.service.LoginService;

@Service("LoginService")
public class LoginServiceImp implements  LoginService {
	
	@Autowired
	UserMapper usrOp;
	

	@Override
	public boolean vaildUserName(String userName, String password) {
		
		if(usrOp.getUser(userName).getPassword().equals(password))
			return true;
		return false;
		
		
		
	}

	@Override
	public boolean isExit(String userName) {
		if (usrOp.getUser(userName)!=null)
			return true;
		return false;
	}

	@Override
	public boolean isLoged(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String userName=(String)session.getAttribute("userName");
		String password=(String)session.getAttribute("password");
		if(usrOp.getUser(userName)!=null&&usrOp.getUser(userName).getPassword().equals(password))
			return true;
		return false;
	}

	@Override
	public ModelMap getReturnMap(String status) {
		ModelMap modelMap=new ModelMap();
	
		if(status=="success"){
			modelMap.put("code", 200 );
			modelMap.put("message", "success");
			modelMap.put("reslut", true);
			return  modelMap;
		}
		if(status=="wrongpsd"){
			 	modelMap.put("code", 400);
				modelMap.put("message", "password wrong");
				modelMap.put("reslut", false);
				return  modelMap;	
		}	
		if(status=="wronguser"){
			 	modelMap.put("code", 500);
				modelMap.put("message", "user not exit");
				modelMap.put("reslut", false);
				return  modelMap;	
			
		 }
		 return  modelMap;
		
		}

	@Override
	public void addSession(HttpServletRequest request, String userName, String password) {
		HttpSession session=request.getSession();
		session.setAttribute("userName", userName);
		session.setAttribute("password", password);
		
	}

	@Override
	public Map<String, Object> getUserMap(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String userName=(String)session.getAttribute("userName");
		Map<String,Object> user=new HashMap<String,Object>();
		user.put("username", usrOp.getUser(userName).getUserName());
		user.put("usertype", usrOp.getUser(userName).getUserType());
	
		return  user;
	
		
	}

	

}
