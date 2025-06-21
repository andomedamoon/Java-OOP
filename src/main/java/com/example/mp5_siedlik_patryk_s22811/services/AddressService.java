package com.example.mp5_siedlik_patryk_s22811.services;

import com.example.mp5_siedlik_patryk_s22811.repository.AddressRepository;
import com.example.mp5_siedlik_patryk_s22811.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
