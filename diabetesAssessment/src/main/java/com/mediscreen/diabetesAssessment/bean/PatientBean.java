package com.mediscreen.diabetesAssessment.bean;

import com.mediscreen.diabetesAssessment.constant.Gender;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class PatientBean {

    private final Long id;

    private final String lastName;

    private final String firstName;

    private final LocalDate birthdate;
    private final int age;

    private final Gender gender;

    private final String address;

    private final String phone;

    public PatientBean(Long id, String lastName, String firstName, LocalDate birthdate, Gender gender, String address, String phone) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;

        this.age = Period.between(birthdate, LocalDate.now()).getYears();
    }

}
