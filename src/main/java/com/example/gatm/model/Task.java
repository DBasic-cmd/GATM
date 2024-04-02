package com.example.gatm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityScan("com.example.gatm")
@ComponentScan("com.example.gatm")
@EnableJpaRepositories("com.example.gatm")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "Id")
    private Long Id;
    private String title;
    private String type;
    private Date dueDate;

    private String description;

    }


