package com.example.vaccineManagement.Service;

import com.example.vaccineManagement.Dtos.RequestDtos.UpdateEmailDto;
import com.example.vaccineManagement.Repositery.UserRepositery;
import com.example.vaccineManagement.model.Dose;
import com.example.vaccineManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepositery userRepositery;
    public User addUser(User user) {
        System.out.println("The user is "+user);
        user= userRepositery.save(user);
        return user;
    }
    public Date getVaccineDate(Integer userId){

        User user=userRepositery.findById(userId).get();

        Dose dose= user.getDose();
        return dose.getVaccinationDate();

    }

    public String updateEmail(UpdateEmailDto updateEmailDto) {
        User user=userRepositery.findById(updateEmailDto.getUserId()).get();
        //Modify the entity with new parameters
        user.setEmailId(updateEmailDto.getNewEmailId());

        userRepositery.save(user);
        return "Email Updated Successfully "+updateEmailDto.getNewEmailId();
    }

    public User getUserByEmail(String emailId) {
       User user= userRepositery.findByEmailId(emailId);
       return user;
    }


}
