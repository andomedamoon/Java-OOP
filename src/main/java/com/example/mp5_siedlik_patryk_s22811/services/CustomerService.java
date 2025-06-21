package com.example.mp5_siedlik_patryk_s22811.services;

import com.example.mp5_siedlik_patryk_s22811.model.Address;
import com.example.mp5_siedlik_patryk_s22811.model.Customer;
import com.example.mp5_siedlik_patryk_s22811.model.Order;
import com.example.mp5_siedlik_patryk_s22811.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void addAddressToCustomer(Customer customer, Address address) {
        if (address.getCustomer() != null) {
            address.getCustomer().getAddresses().remove(address);
        }
        customer.getAddresses().add(address);
        address.setCustomer(customer);
        customerRepository.save(customer);
    }

    @Transactional
    public void removeAddressFromCustomer(Customer customer, Address address) {
        customer.getAddresses().remove(address);
        address.setCustomer(null);
        customerRepository.save(customer);
    }

    @Transactional
    public void addOrderToCustomer(Customer customer, Order order) {
        customer.getOrdersById().put(order.getOrderNumber(), order);
        order.setCustomer(customer);
        customerRepository.save(customer);
    }

    @Transactional
    public void removeOrderFromCustomer(Customer customer, Order order) {
        customer.getOrdersById().remove(order.getOrderNumber());
        order.setCustomer(null);
        customerRepository.save(customer);
    }
}