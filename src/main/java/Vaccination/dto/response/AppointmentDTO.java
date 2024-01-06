package Vaccination.dto.response;

import Vaccination.models.Doctor;
import Vaccination.models.Patient;
import Vaccination.models.VaccinationCenter;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    int doseNumber;
    Patient patient;
    UUID docID;
    String docName;
    UUID vcID;
    String vaccinationCenterName;
}