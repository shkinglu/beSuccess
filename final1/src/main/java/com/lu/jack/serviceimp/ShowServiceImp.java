package com.lu.jack.serviceimp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.lu.jack.dao.Product;
import com.lu.jack.dao.ProductMapper;
import com.lu.jack.dao.TrxMapper;
import com.lu.jack.service.ShowService;

@Service("ShowService")
public class ShowServiceImp implements  ShowService {
	
	@Resource
	ProductMapper   productDao;
	
	
	@Resource
    TrxMapper 	trxDao;

	@Override
	public Map<String, Object> showProduct(int id) {
		
		 Map<String,Object> returnProdcutMap=new HashMap<String,Object>();
			Product oneProduct=productDao.getProduct(id);
			returnProdcutMap.put("id", id);
			returnProdcutMap.put("price", oneProduct.getBuyPrice());
			returnProdcutMap.put("title", oneProduct.getTitle());
			returnProdcutMap.put("image", oneProduct.getImage());
			returnProdcutMap.put("summary", oneProduct.getSummary());
			returnProdcutMap.put("detail", oneProduct.getDetail());
			
			try {
				trxDao.saledProduct(id);
				
				returnProdcutMap.put("buyPrice", trxDao.saledProduct( id).getPrice());
				
		
				returnProdcutMap.put("isBuy",true );
				returnProdcutMap.put("isSell",true );
			} catch (Exception e) {
				returnProdcutMap.put("buyPrice", null);

				returnProdcutMap.put("isBuy",false );
				returnProdcutMap.put("isSell",false );
			}
			return returnProdcutMap;
	}

	@Override
	public List<Object> showAllProduct() {
		
		List<Product> productsList=  productDao.getProductList();
		 
		 //新List准备返回
		 List<Object> list=new ArrayList<Object>();
		 
		 for(int i=0;i<productsList.size();i++){
			 Product   product= new  Product();
			 
			 product.setId(productsList.get(i).getId());
			 product.setImage(productsList.get(i).getImage());
			 product.setPrice((int)(productsList.get(i).getBuyPrice()));
			 product.setTitle(productsList.get(i).getTitle());
			 product.setIsBuy(	(trxDao.saledProduct(productsList.get(i).getId())==null)?false:true);
			 product.setIsSold((trxDao.saledProduct(productsList.get(i).getId())==null)?false:true);
			 list.add(product);

			 
			
		 }
		
		
		
			return list;
		}

	@Override
	public ModelAndView deleteProduct(int id) {
		 ModelAndView	modelAndView=new ModelAndView();
			try {
				productDao.deleteProduct(id);
				modelAndView.addObject("code", 200);
				modelAndView.addObject("message", "delete successful");
				modelAndView.addObject("result", true);
				
	
			} catch (Exception e) {
				modelAndView.addObject("code", 405);
				modelAndView.addObject("message", "Failed");
				modelAndView.addObject("result", false);
			}
			return modelAndView;
	}

	@Override
	public ModelAndView buyProduct(int id) {
		
		ModelAndView	modelAndView=new ModelAndView();
		
		try {
			Product product=productDao.getProduct(id);
			product.setBuyTime(new Date().getTime());
		

			
			
			trxDao.SaveTrx(product);
			modelAndView.addObject("code", 200);
			modelAndView.addObject("message", "buy successful");
			modelAndView.addObject("result", true);
			

		} catch (Exception e) {
			modelAndView.addObject("code", 405);
			modelAndView.addObject("message", "insufficent fund");
			modelAndView.addObject("result", false);
		}
		
		return modelAndView;
		
	}

	@Override
	public Map<String, Object> showPurchasedProduct() {
		Map<String, Object> returnSaledList=new HashMap<String, Object>();
		
		List<Product> saledList=productDao.getSaledProductList();
	
		returnSaledList.put("buyList", saledList);
	
		return returnSaledList;
	}

	}

