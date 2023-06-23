package com.example.vaccineManagement.controllers;

import com.example.vaccineManagement.Dtos.RequestDtos.UpdateEmailDto;
import com.example.vaccineManagement.Service.UserService;
import com.example.vaccineManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/add")
    public User addUser(@RequestBody User user){
       return userService.addUser(user);

    }

    @GetMapping("/getVaccinationDate")
    public Date getVaccineDate(@RequestParam("userId")Integer userId){
        return userService.getVaccineDate(userId);
    }
    @PutMapping("/UpdateEmailId")
    public String UpdateEmail(@RequestBody UpdateEmailDto updateEmailDto){
        return userService.updateEmail(updateEmailDto);
    }
    @GetMapping("/getByEmail/{emailId}")
    public User getUserByEmail(@PathVariable("emailId")String emailID){
        User user=userService.getUserByEmail(emailID);
        return user;
    }
}
