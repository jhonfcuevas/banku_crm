package com.bankucrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankucrm.entities.CustomerEntity;

import java.io.Serializable;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<CustomerEntity, Serializable> {
	
	public CustomerEntity findByIdentification(int identification);
}
