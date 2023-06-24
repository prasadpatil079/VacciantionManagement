package com.example.vaccineManagement.Repositery;

import com.example.vaccineManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface UserRepositery extends JpaRepository<User,Integer> {

    //just define them to execute
    User findByEmailId(String emailId);
    //prebuild functions : and you can use it directly



}
