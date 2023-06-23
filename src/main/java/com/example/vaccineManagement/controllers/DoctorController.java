package com.example.vaccineManagement.controllers;

import com.example.vaccineManagement.Dtos.RequestDtos.AssociateDocDto;
import com.example.vaccineManagement.Service.DoctorService;
import com.example.vaccineManagement.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
     private DoctorService doctorService;
    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor){
        try{
            return doctorService.addDoc(doctor);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/associateWithCenter")
    public ResponseEntity<String> associateDoctor(@RequestBody AssociateDocDto associateDocDto){
        try{
            String result=doctorService.associateDoc(associateDocDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getDoctorList")
    public ResponseEntity<?> getDoctor(@RequestParam Integer centerId){
        try{
            List<String> result=doctorService.getDocBycenterId(centerId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getMaleDoctorList")
    public ResponseEntity<?> getMaleDoctor(@RequestParam Integer centerId){
        try{
            List<String> result=doctorService.getMaleDocBycenterId(centerId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getFemaleDoctorList")
    public ResponseEntity<?> getFemaleDoctor(@RequestParam Integer centerId){
        try{
            List<String> result=doctorService.getFemaleDocBycenterId(centerId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getDoctorByAgeList")
    public ResponseEntity<?> getDoctorByAge(@RequestParam Integer centerId){
        try{
            List<String> result=doctorService.getDocByAge(centerId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getDoctorList")
    public ResponseEntity<?> getDoctorList(){
        try{
            List<String> result=doctorService.getDocList();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getMaleDoctorAgeList")
    public ResponseEntity<?> getDoctorMaleAgeList(){
        try{
            List<String> result=doctorService.getDocMaleList();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getRatio")
    public ResponseEntity<String> getGenderRatio(){
        try{
            String result=doctorService.getDocRatio();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/Update")
    public ResponseEntity<String> UpdateDoctor(@RequestBody Doctor doctor ) {
        try {
            String result = doctorService.UpdateDoctor(doctor);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
