package com.lu.jack.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface ShowService {
		
	public Map<String, Object> showProduct(int id);
	
	public List<Object> showAllProduct();
	
	public ModelAndView deleteProduct(int id);
	
	
	public ModelAndView buyProduct(int id);
	
	public Map<String, Object> showPurchasedProduct();
	
}
