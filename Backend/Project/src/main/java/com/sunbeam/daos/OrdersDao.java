package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer>{
	Orders findByOrderid(int id);
}
