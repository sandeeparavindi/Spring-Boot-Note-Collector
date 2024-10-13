package org.example.springbootnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNoteApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

}
