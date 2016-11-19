package com.lu.jack.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lu.jack.dao.Product;
import com.lu.jack.dao.ProductMapper;
import com.lu.jack.dao.User;
import com.lu.jack.dao.UserMapper;
import com.lu.jack.service.LoginService;

/**
 * @author cnluja
 *
 */
@Controller
public class PublishController {
	
	@Resource
	UserMapper userDao;
	
	@Resource
	
	ProductMapper productDao;
	
	@Resource
	LoginService  loginService;
	
	/**
	 * @author cnluja
	 *
	 */
	@RequestMapping("/public")
	public String publish(HttpServletRequest request, ModelMap modelMap){
		Map<String,Object> root=new HashMap<String,Object>();
		
		
		if(loginService.isLoged(request)){
			
			root.put("user", loginService.getUserMap(request));
			
			modelMap.addAllAttributes(root);
			return "public";
		}
		return "login";
	}
	
	
	/**
	 * @author cnluja
	 *
	 */
	@RequestMapping("/publicSubmit")
	public String publishSubmit( @RequestParam(value="title") String title,@RequestParam(value="image") String  image ,
			@RequestParam(value="detail") String  detail ,@RequestParam(value="price") double  price ,@RequestParam(value="summary") String  summary,
			HttpServletRequest request, ModelMap modelMap){
		HttpSession session=request.getSession();
		User user=userDao.getUser((String)session.getAttribute("userName"));
		
	
		
		if(user!=null&&user.getUserType()!=0){
			Product product = new Product();
			product.setDetail(detail);
			product.setImage(image);
			product.setBuyPrice(price);
			product.setSummary(summary);
			product.setTitle(title);
			
			
			try {
				productDao.addProduct(product);	
				modelMap.put("product", product);
				return "publicSubmit";
				
			} catch (Exception e) {
				return "publicSubmit";
			}
		}	
			
		return  "login";
	}
	
	
}