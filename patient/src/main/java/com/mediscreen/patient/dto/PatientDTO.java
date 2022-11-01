package com.mediscreen.patient.dto;

import com.mediscreen.patient.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {

    private Long id;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Birthdate is required")
    private LocalDate birthdate;

    @NotBlank(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String phone;

}
