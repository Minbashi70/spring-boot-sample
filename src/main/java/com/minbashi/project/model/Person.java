package com.minbashi.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Person {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "AGE")
    private Integer age;

}
