package Vaccination.service;

import Vaccination.models.Doctor;
import Vaccination.models.VaccinationCenter;
import Vaccination.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    public Doctor registerDoctor(Doctor obj){
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterService.getMinimumDoctorCountVC();
        VaccinationCenter vaccinationCenter = vaccinationCenterList.get(0);
        obj.setVaccinationCenter(vaccinationCenter);
        vaccinationCenterService.updateDocCountByOne(vaccinationCenter);

        doctorRepository.save(obj);
        return obj;
    }

    public List<Doctor> getMinimumDoctorOnTheBasisOfVC(UUID vcid){
        return doctorRepository.getMinimumDoctorOnTheBasisOfVC(vcid);
    }

    public void updatePatientCountByOne(Doctor doctor){
        UUID id = doctor.getId();
        int patientCount = doctor.getPatientCount() + 1;
        doctorRepository.updatePatientCountByOne(id, patientCount);
    }

    public void addPatientVsDoctor(UUID patientId, UUID doctorId){
        doctorRepository.insertIntoDoctorVsPatientsTable(doctorId, patientId);
    }

}
