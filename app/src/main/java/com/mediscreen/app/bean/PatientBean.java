package com.mediscreen.app.bean;

import com.mediscreen.app.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientBean {

    private Long id;
    private String lastName;
    private String firstName;
    private String birthdate;
    private Gender gender;
    private String address;
    private String phone;

}
