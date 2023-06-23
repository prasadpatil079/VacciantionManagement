package com.example.vaccineManagement.Service;

import com.example.vaccineManagement.Dtos.RequestDtos.AssociateDocDto;
import com.example.vaccineManagement.Enums.Gender;
import com.example.vaccineManagement.Exceptions.*;
import com.example.vaccineManagement.Repositery.DoctorRepository;
import com.example.vaccineManagement.Repositery.VaccinationCenterRepository;
import com.example.vaccineManagement.model.Doctor;
import com.example.vaccineManagement.model.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private  DoctorRepository doctorRepository;

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public String addDoc(Doctor doctor) throws EmailIdEmptyException, DoctorAlreadyExistException {

        if(doctor.getEmailId()==null){
            throw new EmailIdEmptyException("EmailId is mandatory");
        }
        if(doctorRepository.findByEmailId(doctor.getEmailId())!=null){
            throw new DoctorAlreadyExistException("Doctor with emailId already exist");
        }
        doctorRepository.save(doctor);
        return "Doctor Saved Successfully";
    }

    public String associateDoc(AssociateDocDto associateDocDto)throws DoctorNotFound, CenterNotFound {

         Integer docId=associateDocDto.getDocId();

        Optional<Doctor>doctorOpt=doctorRepository.findById(docId);

        Optional<VaccinationCenter>VcOpt=vaccinationCenterRepository.findById(associateDocDto.getCenterId());
         if(!doctorOpt.isPresent()){
             throw new DoctorNotFound("Doctor id is wrong");
        }
         if (VcOpt.isEmpty()){
             throw new CenterNotFound("Center id is wrong");
         }
         Doctor doctor=doctorOpt.get();
         VaccinationCenter vaccinationCenter=VcOpt.get();

         doctor.setVaccinationCenter(vaccinationCenter);//setting the foreign

        //set bidirectional map
        //adding this doctor to the list of doctors of that vaccination center
        vaccinationCenter.getDoctorList().add(doctor);
        vaccinationCenterRepository.save(vaccinationCenter);
        return "Doctor associated to center successfully";
    }

    public List<String> getDocBycenterId(Integer centerId) throws VaccinationAddressNotFound {

        Optional<VaccinationCenter>centerOpt=vaccinationCenterRepository.findById(centerId);
        if(centerOpt.isEmpty()){
            throw new VaccinationAddressNotFound("CenterId not found");
        }
        VaccinationCenter vaccinationCenter=centerOpt.get();
        List<Doctor> list=vaccinationCenter.getDoctorList();
        List<String> nameList=new ArrayList<>();
        for(Doctor doc:list){
            nameList.add(doc.getName());
        }
        return nameList;
    }

    public List<String> getMaleDocBycenterId(Integer centerId)throws VaccinationAddressNotFound {

        Optional<VaccinationCenter>centerOpt=vaccinationCenterRepository.findById(centerId);
        if(centerOpt.isEmpty()){
            throw new VaccinationAddressNotFound("CenterId not found");
        }
        VaccinationCenter vaccinationCenter=centerOpt.get();
        List<Doctor> list=vaccinationCenter.getDoctorList();
        List<String> nameList=new ArrayList<>();
        for(Doctor doc:list){
            if(doc.getGender().equals(Gender.MALE)) {
                nameList.add(doc.getName());
            }
        }
        return nameList;
    }
    public List<String> getFemaleDocBycenterId(Integer centerId) throws VaccinationAddressNotFound{
        Optional<VaccinationCenter>centerOpt=vaccinationCenterRepository.findById(centerId);
        if(centerOpt.isEmpty()){
            throw new VaccinationAddressNotFound("CenterId not found");
        }
        VaccinationCenter vaccinationCenter=centerOpt.get();
        List<Doctor> list=vaccinationCenter.getDoctorList();
        List<String> nameList=new ArrayList<>();
        for(Doctor doc:list){
            if(doc.getGender().equals(Gender.FEMALE)) {
                nameList.add(doc.getName());
            }
        }
        return nameList;
    }

    public List<String> getDocByAge(Integer centerId) throws VaccinationAddressNotFound{
        Optional<VaccinationCenter>centerOpt=vaccinationCenterRepository.findById(centerId);
        if(centerOpt.isEmpty()){
            throw new VaccinationAddressNotFound("CenterId not found");
        }
        VaccinationCenter vaccinationCenter=centerOpt.get();
        List<Doctor> list=vaccinationCenter.getDoctorList();
        List<String> nameList=new ArrayList<>();
        for(Doctor doc:list){
            if(doc.getAge()>=40) {
                nameList.add(doc.getName());
            }
        }
        return nameList;
    }

    public List<String> getDocList() {
        List<Doctor> list=doctorRepository.findAll();
        List<String> nameList=new ArrayList<>();
        for(Doctor doc:list){
            if(doc.getAppointmentList().size()>=10){
                nameList.add(doc.getName());
            }
        }
        if(nameList.size()==0){
            throw new RuntimeException("no doc with  10 appointment found");
        }
        return nameList;
    }

    public List<String> getDocMaleList() {
        List<Doctor> list=doctorRepository.findAll();
        List<String> nameList=new ArrayList<>();
        for(Doctor doc:list){
            if(doc.getAge()>=40){
                nameList.add(doc.getName());
            }
        }
        if(nameList.size()==0){
            throw new RuntimeException("no doc with age found");
        }
        return nameList;
    }

    public String getDocRatio() throws FemalesAreZero,MaleAreZero{
        List<Doctor> list=doctorRepository.findAll();
        int male=0;
        int female=0;
        for(Doctor doc:list){
            if(doc.getGender().equals(Gender.FEMALE)){
                female++;
            }else {
                male++;
            }
        }
        //checking females as we want ratio
        if(female==0){
            throw new FemalesAreZero("Ratio cannot be calculated,No female doctor found;");
        }
        //checking males as we want ratio
        if(male==0){
            throw new MaleAreZero("Ratio cannot be calculated,No Male doctor found;");
        }
        int ratio=getratio(male,female);
        int rMale = male / ratio;
        int rFemale = female / ratio;
        return "Ratio of male to female is "+rMale+ ":"+rFemale;
    }
    public  int getratio(Integer A ,Integer B){
        if(B==0){
            return 0;
        }
        return getratio(A,A%B);

    }

    public String UpdateDoctor(Doctor doctor) throws DoctorNotFound{
        Doctor doctor1=null;
         doctor1= doctorRepository.findByEmailId(doctor.getEmailId());

         if(doctor1==null){
             new DoctorNotFound("Email id not found,Wrong EmailId!!!");
         }
         doctor1.setAge(doctor.getAge());
         doctor1.setGender(doctor.getGender());
         doctor1.setDocId(doctor1.getDocId());
        doctor1.setName(doctor.getName());
        doctorRepository.save(doctor1);
        return "doctor Details updated successfully";

    }
}
