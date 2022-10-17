package com.mediscreen.patient.repository;

import com.mediscreen.patient.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    public Optional<PatientEntity> findByLastNameAndFirstName(String lastName, String firstName);

}
