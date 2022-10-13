package com.mediscreen.app.bean;

import com.mediscreen.app.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientBean {

    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birthdate;
    private Gender sex;
    private String address;
    private String phone;


}
