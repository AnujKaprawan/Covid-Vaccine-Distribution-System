package Vaccination.dto.request;

import Vaccination.enums.VaccinationCenterPrefrence;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateAppointmentDTO {
    UUID id;
    VaccinationCenterPrefrence vaccinationCenterPrefrence;
}