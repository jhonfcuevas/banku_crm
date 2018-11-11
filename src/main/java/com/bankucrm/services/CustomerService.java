package com.bankucrm.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankucrm.entities.CustomerEntity;
import com.bankucrm.repository.CustomerRepository;

@Service("customerService")
public class CustomerService {

	@Autowired
	@Qualifier("customerRepository")
	private CustomerRepository customerRepository;
	
	public ResponseEntity<Object> createCustomer (CustomerEntity customerEntity) {
		try {
			customerEntity.setCreateDate(new Date());
			customerEntity.setModifyDate(new Date());
			customerEntity.setEnabled(true);
			CustomerEntity customerEntity2 = customerRepository.save(customerEntity);

			return new ResponseEntity<>(customerEntity2, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean removeClient (int identification) {
		try {
			CustomerEntity customerEntity = customerRepository.findByIdentification(identification);
			if (customerEntity != null) {
				customerRepository.delete(customerEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> updateClient (CustomerEntity customerEntity) {
		try {
			CustomerEntity customerEntity2 = customerRepository.findByIdentification(customerEntity.getIdentification());
		
			if (customerEntity2 !=null) {
				customerEntity.setCreateDate(customerEntity2.getCreateDate());
				customerEntity.setModifyDate(new Date());
				CustomerEntity customerEntity3 = customerRepository.save(customerEntity);
				return new ResponseEntity<>(customerEntity3, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> findAllClients () {
		try {
			List<CustomerEntity> customerEntities = customerRepository.findAll();
			return new ResponseEntity<>(customerEntities, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
