package com.example.vaccineManagement.Service;

import com.example.vaccineManagement.Exceptions.VaccinationAddressNotFound;
import com.example.vaccineManagement.Repositery.VaccinationCenterRepository;
import com.example.vaccineManagement.model.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccinationService {

    @Autowired
     VaccinationCenterRepository vaccinationCenterRepository;
    public String addcenter(VaccinationCenter vaccinationCenter) throws VaccinationAddressNotFound {
        if(vaccinationCenter.getAddress()==null){
            throw new VaccinationAddressNotFound("Address Not Found");
        }
        vaccinationCenterRepository.save(vaccinationCenter);
        return "Center Added Successfully"+ vaccinationCenter.getAddress();
    }

    public List<VaccinationCenter> getcenter() {
        List<VaccinationCenter> list=vaccinationCenterRepository.findAll();
        List<VaccinationCenter> Vclist=new ArrayList<>();
        for(VaccinationCenter vc:list){
            if(vc.getDoctorList().size()>5){
                Vclist.add(vc);
            }
        }
        if(Vclist.size()==0){
            throw new RuntimeException("NO centers found");
        }
        return Vclist;
    }
}
