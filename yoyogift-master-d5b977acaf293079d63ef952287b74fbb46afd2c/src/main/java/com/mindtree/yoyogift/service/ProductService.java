package com.mindtree.yoyogift.service;

import java.util.List;

import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.exception.service.ListEmptyException;
import com.mindtree.yoyogift.exception.service.ServiceException;


public interface ProductService {
	public Product addProduct(Product product) throws ServiceException;
	public List<ProductDto> findAll() throws ServiceException;
	public ProductDto getDetails(long productId) throws ServiceException;
	public Product updateProduct(Product product) throws ServiceException;
	public Product deleteProduct(long productId) throws ServiceException;
}
