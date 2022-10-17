package com.mediscreen.patient.entity;


import com.mediscreen.patient.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The last name is required")
    @Column(name = "last_name", length = 100)
    private String lastName;

    @NotBlank(message = "The first name is required")
    @Column(name = "first_name", length = 100)
    private String firstName;

    @NotBlank(message = "The birthdate is required")
    private LocalDate birthdate;

    @NotBlank(message = "The gender is required")
    @Enumerated(EnumType.STRING)
    private Gender sex;

    private String address;

    @Column(name = "phone", length = 50)
    private String phone;

}
