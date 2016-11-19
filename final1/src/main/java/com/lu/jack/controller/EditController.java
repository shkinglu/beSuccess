package com.lu.jack.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lu.jack.dao.Product;
import com.lu.jack.dao.ProductMapper;
import com.lu.jack.dao.User;
import com.lu.jack.dao.UserMapper;
import com.lu.jack.service.LoginService;


@Controller
public class EditController {
	
	@Resource
	LoginService loginService;
	
	@Resource
	UserMapper userDao;
	
	@Resource
	ProductMapper  productDao;
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String enterEditPage(HttpServletRequest request, ModelMap modelMap,@RequestParam int id ){
		
		HttpSession session=request.getSession();
		User user=userDao.getUser((String)session.getAttribute("userName"));
		
		
		
		if(user!=null&&user.getUserType()!=0){
			modelMap.addAttribute("user" ,loginService.getUserMap(request));
				Product product=productDao.getProduct(id);
				if(product!=null){
				modelMap.addAttribute("product", product);
				
				return "edit";
			
			}
			return "edit";
		}
		
		return "login";
	}
	
	
	@RequestMapping(value="/editSubmit",method=RequestMethod.POST)
	public String editSubmit(@RequestParam int id ,@Param(value="title") String title,@RequestParam(value="image") String  image,
			@Param(value="detail") String  detail ,@Param(value="price") double price ,@Param(value="summary") String  summary,HttpServletRequest request,ModelMap modelMap){
	
		HttpSession session=request.getSession();
		User user=userDao.getUser((String)session.getAttribute("userName"));
		
		
		
		if(user!=null&&user.getUserType()!=0){
			modelMap.addAttribute("user" ,loginService.getUserMap(request));
			
			Product product=productDao.getProduct(id);
			product.setDetail(detail);
			 product.setImage(image);
			 product.setBuyPrice(price);
			 product.setSummary(summary);
			 product.setTitle(title);
			 productDao.updateProduct(product);
			int i=productDao.updateProduct(product);
			 if(i>0){	
				
				modelMap.addAttribute("product",product);
				
					return "editSubmit";
				}
		
		return "editSubmit";
		}
		
		return "login";
	}
}
