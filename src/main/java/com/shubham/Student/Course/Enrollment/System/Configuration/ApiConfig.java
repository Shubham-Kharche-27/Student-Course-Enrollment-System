package com.shubham.Student.Course.Enrollment.System.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
