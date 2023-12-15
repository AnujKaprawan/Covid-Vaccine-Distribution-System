package Vaccination.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String gender;
    @Column(unique = true)
    String aadharNumber;
    int doseCount;
    String vaccinationPrefrence;
    String address;
    @Column(unique = true)
    long phoneNumber;
    @Column(unique = true)
    String email;
    String password;
}

