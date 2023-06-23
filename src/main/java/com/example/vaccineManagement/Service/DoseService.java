package com.example.vaccineManagement.Service;

import com.example.vaccineManagement.Repositery.DoseRepository;
import com.example.vaccineManagement.Repositery.UserRepositery;
import com.example.vaccineManagement.model.Dose;
import com.example.vaccineManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;
    @Autowired
    UserRepositery userRepositery;

    public String giveDose(String doseId,Integer userId){

        User user=userRepositery.findById(userId).get();
        // an entity of that model has been created
        //this entity will be saved in the database

        Dose dose=new Dose();
        //setting its attributes
        dose.setDoseID(doseId);
        dose.setUser(user);
        //setting the child object in that corresponding attribute;
        user.setDose(dose);

        userRepositery.save(user);

        //save the entity
        //doseRepository.save(dose);



        return "Dose Given to user successfully";

    }
}
