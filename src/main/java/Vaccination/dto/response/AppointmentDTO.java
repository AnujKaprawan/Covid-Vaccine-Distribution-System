package Vaccination.dto.response;

import Vaccination.models.Doctor;
import Vaccination.models.Patient;
import Vaccination.models.VaccinationCenter;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    int doseNumber;
    Patient patient;
    Doctor doctor;
    VaccinationCenter vaccinationCenter;
}
