package com.example.vaccineManagement.Service;

import com.example.vaccineManagement.Dtos.RequestDtos.AppointmentRequestDto;
import com.example.vaccineManagement.Exceptions.DoctorNotFound;
import com.example.vaccineManagement.Exceptions.UserNotFound;
import com.example.vaccineManagement.Exceptions.VaccinationAddressNotFound;
import com.example.vaccineManagement.Repositery.AppointmentRepository;
import com.example.vaccineManagement.Repositery.DoctorRepository;
import com.example.vaccineManagement.Repositery.UserRepositery;
import com.example.vaccineManagement.model.Appointment;
import com.example.vaccineManagement.model.Doctor;
import com.example.vaccineManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepositery userRepositery;
    public String bookAppointment(AppointmentRequestDto appointmentRequestDto) throws DoctorNotFound, UserNotFound {

        Integer docId=appointmentRequestDto.getDocId();
         Optional<Doctor>docOpt= doctorRepository.findById(docId);
         if(docOpt.isEmpty()){
             throw new DoctorNotFound("DoctorId not found");
         }
        Integer userId=appointmentRequestDto.getUserId();
        Optional<User>userOpt= userRepositery.findById(userId);
        if (userOpt.isEmpty()){
            throw new UserNotFound("userId not found");
        }
        Doctor doctor=docOpt.get();
        User user=userOpt.get();

        Appointment appointment= new Appointment();
        //setting the foreign key attributes
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        //creating the object and setting of its attribute
        appointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentRequestDto.getAppointmentTime());


        appointment= appointmentRepository.save(appointment);



//        List<Appointment>appointmentList=doctor.getAppointmentList();
//        Appointment lastApp=appointmentList.get(appointmentList.size()-1);
//        int id= lastApp.getId();
//        appointment.setId(id);
        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepositery.save(user);

        return "Appointment booked successfully";




    }
}
