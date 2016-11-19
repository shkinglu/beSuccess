package com.lu.jack.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public interface LoginService {
	public boolean vaildUserName(String userName,String password);
	
	public boolean   isExit(String userName);
	
	public boolean isLoged(HttpServletRequest request);
	
	public ModelMap getReturnMap(String status);

	public void addSession(   HttpServletRequest request, String userName, String password);

	public Map<String,Object> getUserMap(HttpServletRequest request);
	
	
	
	
}
