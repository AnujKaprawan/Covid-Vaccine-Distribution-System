package Vaccination.repository;

import Vaccination.models.VaccinationCenter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {
    @Query(value = "select * from vaccination_center where doctor_count = (select min(doctor_count) from vaccination_center)", nativeQuery = true)
    public List<VaccinationCenter> getMinimumDoctorVaccinationCenter();

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set doctor_count=:docCount where id=:id", nativeQuery = true)
    public void updateDocCountByOne(UUID id, int docCount);

}
