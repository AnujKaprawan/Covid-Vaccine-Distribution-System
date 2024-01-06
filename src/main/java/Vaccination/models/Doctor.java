package Vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String docDegree;
    @ManyToOne
    @JsonIgnore
    VaccinationCenter vaccinationCenter;
    int patientCount;
    @ManyToMany
    List<Patient> patients;
}