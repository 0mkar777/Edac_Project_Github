package com.sunbeam.services;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import com.sunbeam.entities.Products;

public interface ProductService{
	Products findByProductId(int id);
	List<Products> findAll();
	List<Products> findByCategory(String category);
	List<Products> findByCategoryOrderByPriceAsc(String category);
	List<Products> findByCategoryOrderByPriceDesc(String category);
	List<Products> findByBrand(String brand);
	List<Products> findByOrderByPriceAsc();
	List<Products> findByOrderByPriceDesc();
	List<Products> findByOrderByRatingAsc();
	List<Products> findByOrderByRatingDesc();
	Products saveProduct(Products products,MultipartFile image1,MultipartFile image2,MultipartFile image3);
	List<Products> findProductLikeName(String name);
	@Modifying
	@Query("delete from products p where p.pname=:pname")
	void deleteProductsByPname(@Param("pname") String pname);
	void deleteByProductId(Integer id);
	List<String> findDistinctCategory();
	List<String> findDistinctBrand();
//	void updatePrice(double price, int productId);
	Products updateProductPriceByProductId(double price, int ProductId);
}
