package com.mindtree.yoyogift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> 
{

	List<FeedBack> findByProduct(Product product);

}
