package com.mindtree.yoyogift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.Coupouns;
import com.mindtree.yoyogift.entity.User;

@Repository
public interface CoupounRepository extends JpaRepository<Coupouns, Long> {
//	public List<Coupouns> findByReedemedBy(User user);
//	public List<Coupouns> findByReedemedByOrderByDateOfExpiryAsc(User user);
//	public List<Coupouns> findByReedemedByOrderByDateOfExpiryAscIsRedeemedAsc(User user);
	public List<Coupouns> findByReedemedByOrderByIsRedeemedAscDateOfExpiryAsc(User user);
}
