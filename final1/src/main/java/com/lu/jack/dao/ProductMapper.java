package com.lu.jack.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lu.jack.utility.MyBlobTypeHandler;

public interface ProductMapper {
	@Results({
		@Result(property="id",column="id"),
		@Result(property="buyPrice",column="price"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="summary",column="abstract"),
		@Result(property="detail",column="text",typeHandler= MyBlobTypeHandler.class)
	})
	@Select("select * from content where id=#{id}")
	public Product getProduct(int id );
	
	//每次用一个select 如果数据名字不对应的话都要用一遍results.
	@Results({
		@Result(property="id",column="id"),
		@Result(property="buyPrice",column="price"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="summary",column="abstract"),
		@Result(property="detail",column="text" , typeHandler= MyBlobTypeHandler.class)
	})
	
	@Select("select * from content")
	public List<Product> getProductList();
	
	
	
	@Results({
		@Result(property="id",column="contentId"),
		@Result(property="buyPrice",column="price"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="summary",column="abstract"),
		@Result(property="detail",column="text",typeHandler= MyBlobTypeHandler.class),
		@Result(property="buyTime",column="time"),
		@Result(property="price",column="t.price"),
	})
	
	
	@Select("select * from trx t left join content on content.id=t.contentId")
	public List<Product> getSaledProductList();
	
	
 	@Insert("insert into content(title,price,icon,abstract,text) values (#{product.title},#{product.buyPrice},#{product.image},#{product.summary},#{product.detail})")
	@Options(useGeneratedKeys=true,keyProperty="product.id")
 	public int addProduct(@Param("product") Product product );
	
	
 	
 	@Delete("delete from content where id=#{id}")
 	public int deleteProduct(int id);
 	
 	
//用update 和insert的时候经常忘记穿参数@para
 	@Update("update content set price=#{product.buyPrice},title=#{product.title},icon=#{product.image},abstract=#{product.summary},text=#{product.detail}where id=#{product.id}")
	public int updateProduct(@Param("product")  Product product);	
}


