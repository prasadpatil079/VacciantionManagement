package com.example.vaccineManagement.controllers;

import com.example.vaccineManagement.Exceptions.VaccinationAddressNotFound;
import com.example.vaccineManagement.Service.VaccinationService;
import com.example.vaccineManagement.model.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationController {
    @Autowired
     public VaccinationService vaccinationService;
    @PostMapping("/add")
    public ResponseEntity<String> addCenter(@RequestBody VaccinationCenter vaccinationCenter){

     try {
         String result=vaccinationService.addcenter(vaccinationCenter);
         return new ResponseEntity<>(result, HttpStatus.OK);
     }
     catch(VaccinationAddressNotFound v){
         return new ResponseEntity<>(v.getMessage(),HttpStatus.NOT_FOUND);
     }
    }
}
