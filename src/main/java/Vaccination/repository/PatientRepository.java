package Vaccination.repository;

import Vaccination.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    @Query(value = "select * from patient where email=:patientEmail", nativeQuery = true)
    public Patient getPatientByEmail(String patientEmail);

}