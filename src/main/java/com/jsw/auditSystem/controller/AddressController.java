package com.jsw.auditSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsw.auditSystem.exceptions.AddressInfoNotFoundException;
import com.jsw.auditSystem.exceptions.UserInfoNotFoundException;
import com.jsw.auditSystem.model.Address;
import com.jsw.auditSystem.model.AddressInfo;
import com.jsw.auditSystem.model.JsonAddressObject;
import com.jsw.auditSystem.model.Logger;
import com.jsw.auditSystem.repository.AddressMangoRepository;
import com.jsw.auditSystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMangoRepository addressMangoRepository;

    @GetMapping("/addresses/{id}")
    public Address getAddress(@PathVariable("id") long id){
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
    }

    @Logger("Address updated.")
    @PutMapping("/update-address/{id}")
    public Address updateAddress(@PathVariable("id") long id, @RequestBody Address newAddress){
        return addressRepository.findById(id)
                .map(address -> {
                    address.setAddressName(newAddress.getAddressName());
                    return addressRepository.save(address);
                })
                .orElseThrow(() -> new AddressInfoNotFoundException("Address  not found for this id : " + id));
    }

    @GetMapping("/addresses/mongo/{id}")
    List<JsonAddressObject> getByIdFromMongo(@PathVariable("id") String id){

        List<AddressInfo> infoAddress = addressMangoRepository.findByAddressId(id);
        List<Map<String, String>> listOfMaps = new ArrayList<>();

        for(AddressInfo addressInfo : infoAddress){
            listOfMaps.add(addressInfo.getLogElements());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonAddressObject> jsonAddressObjectsList = listOfMaps.stream().map(map1 ->
                objectMapper.convertValue(map1, JsonAddressObject.class)).collect(Collectors.toList());

        return jsonAddressObjectsList;
    }

    @Logger("Address created.")
    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address){
        return addressRepository.save(address);
    }

}
