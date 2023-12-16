package Vaccination.repository;

import Vaccination.models.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {

}
