package Vaccination.service;


import Vaccination.dto.request.PatientLoginDTO;
import Vaccination.dto.request.PatientSignupDTO;
import Vaccination.dto.response.AppointmentDTO;
import Vaccination.exceptions.PatientDoesNotExistException;
import Vaccination.exceptions.WrongCredentials;
import Vaccination.models.Doctor;
import Vaccination.models.Patient;
import Vaccination.models.VaccinationCenter;
import Vaccination.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @Autowired
    DoctorService doctorService;


    public Patient signUp(PatientSignupDTO patientSignupDTO){
        Patient patient = new Patient();
        patient.setName(patientSignupDTO.getName());
        patient.setGender(patientSignupDTO.getGender());
        patient.setEmail(patientSignupDTO.getEmail());
        patient.setAddress(patientSignupDTO.getAddress());
        patient.setAadharNumber(patientSignupDTO.getAadharNumber());
        patient.setPassword(patientSignupDTO.getPassword());
        patient.setPhoneNumber(patientSignupDTO.getPhoneNumber());
        patient.setVaccinationPrefrence(patientSignupDTO.getVaccinationPrefrence().toString());
        patientRepository.save(patient);
        return patient;
    }


    public Patient login(PatientLoginDTO patientLoginDTO){
        Patient patient = patientRepository.getPatientByEmail(patientLoginDTO.getEmail());
        if(patient == null){
            throw new PatientDoesNotExistException("Patient email Id is not registered in our portal.");
        }
        if(!patient.getPassword().equals(patientLoginDTO.getPassword())){
            throw new WrongCredentials("Patient Entered Wrong Password.");
        }
        return patient;
    }

    public AppointmentDTO createAppointment(String email, String vaccinationCenterPrefrence){
        // 1. get patient by email
        Patient p = patientRepository.getPatientByEmail(email);
        // 2. Identify patient vaccination prefrence
        String vPrefrence = p.getVaccinationPrefrence();
        List<VaccinationCenter> vcList = vaccinationCenterService.getMinimumVCOnTheBasisOfTypeAndPrefrence(vaccinationCenterPrefrence, vPrefrence);
        // 3. Assigning 0th index vaccination center to patient
        VaccinationCenter patientsVC = vcList.get(0);
        // 4. Assign doctor who is handeling minimum number of patients to the current patient
        List<Doctor> docList = doctorService.getMinimumDoctorOnTheBasisOfVC(patientsVC.getId());
        // 5. Take out minimum doctor
        Doctor patientDoctor = docList.get(0);

        /*
         VaccinationCenter -> patients count + 1
         Doctor -> patientCount + 1
         Doctor -> List -> add patient -> Database -> Insert docId, pid into docvs patient table
         return response body -> patient details, patientc vc details, doctor details */

        vaccinationCenterService.updatePatientCountByOne(patientsVC);
        doctorService.updatePatientCountByOne(patientDoctor);
        patientDoctor.getPatients().add(p);
        doctorService.addPatientVsDoctor(p.getId(), patientDoctor.getId());
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoseNumber(p.getDoseCount() + 1);
        appointmentDTO.setPatient(p);
        appointmentDTO.setVaccinationCenter(patientsVC);
        appointmentDTO.setDoctor(patientDoctor);
        return appointmentDTO;
    }

}