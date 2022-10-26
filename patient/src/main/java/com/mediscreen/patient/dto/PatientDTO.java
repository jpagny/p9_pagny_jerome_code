package com.mediscreen.patient.dto;

import com.mediscreen.patient.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private LocalDate birthdate;

    private Gender gender;

    private String address;

    private String phone;

}
