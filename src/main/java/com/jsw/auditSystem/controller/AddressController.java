package com.jsw.auditSystem.controller;

import com.jsw.auditSystem.model.Address;
import com.jsw.auditSystem.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> createAddress(@RequestBody Address address)
    {
        Address saveAccount = addressService.createAddress(address);
        return new ResponseEntity<>(saveAccount, HttpStatus.CREATED);
    }
}
