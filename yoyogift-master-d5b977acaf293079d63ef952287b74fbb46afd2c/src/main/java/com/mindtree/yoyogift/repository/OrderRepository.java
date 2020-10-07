package com.mindtree.yoyogift.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{
	public List<Orders> findAllByProduct(Product product);
	public List<Orders> findAllByOrderDateBetween(Date from, Date to);
}
