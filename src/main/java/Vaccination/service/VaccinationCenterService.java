package Vaccination.service;

import Vaccination.models.VaccinationCenter;
import Vaccination.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    
      public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){
          vaccinationCenterRepository.save(vaccinationCenter); // It is going to save Vaccination center object in the database
          return vaccinationCenter;
      }


    public List<VaccinationCenter> getMinimumDoctorCountVC(){
        List<VaccinationCenter> vaccinationCenters = vaccinationCenterRepository.getMinimumDoctorVaccinationCenter();
        return vaccinationCenters;
    }

    public void updateDocCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int docCount =  vaccinationCenter.getDoctorCount() + 1;
        vaccinationCenterRepository.updateDocCountByOne(id, docCount);
    }
}
