package com.example.vaccineManagement.model;

import com.example.vaccineManagement.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERS")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(name = "user_name")
    private String name;
    private int age;
    @Column(unique = true)
    private String emailId;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String mobileNO;

    @JsonIgnore
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Dose dose;

    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    private List<Appointment> appointmentList =new ArrayList<>();


}
