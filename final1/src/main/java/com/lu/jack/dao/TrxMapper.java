package com.lu.jack.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TrxMapper {
	  @Insert("insert into trx (contentId,personId ,price,time) values (#{product.id}, 0,#{product.buyPrice} ,#{product.buyTime})")	
	  public	int SaveTrx(@Param("product") Product product);
	  
	  

		@Results({

			@Result(property="price",column="price"),
		
		
		})
	  @Select("select * from trx where contentId=#{id}")
	  public Trx saledProduct(int id); 
}
