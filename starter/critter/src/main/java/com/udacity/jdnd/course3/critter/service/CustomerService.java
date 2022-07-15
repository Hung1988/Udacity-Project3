package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer saveCus (Customer customer) {
        if (customer != null) customerRepository.save(customer);
        return customer;
    }

    public Customer getCus (Long cusId) {
        Customer customer = customerRepository.getOne(cusId);
        return customer;
    }

    public List<Customer> getAllCus () {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }
}
