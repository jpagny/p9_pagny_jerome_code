package com.mediscreen.patient.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.patient.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 5)
    private Gender gender;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "phone", length = 50)
    private String phone;

}
