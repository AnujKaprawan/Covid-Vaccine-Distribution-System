package Vaccination.service;

import Vaccination.models.VaccinationCenter;
import Vaccination.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    
      public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){
          vaccinationCenterRepository.save(vaccinationCenter); // It is going to save Vaccination center object in the database
          return vaccinationCenter;
      }

}
