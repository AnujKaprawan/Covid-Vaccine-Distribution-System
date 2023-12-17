package Vaccination.controller;


import Vaccination.dto.request.PatientLoginDTO;
import Vaccination.dto.request.PatientSignupDTO;
import Vaccination.dto.response.GeneralMessageDTO;
import Vaccination.exceptions.PatientDoesNotExistException;
import Vaccination.exceptions.WrongCredentials;
import Vaccination.models.Patient;
import Vaccination.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody PatientSignupDTO patientSignupDTO){
        Patient patient = patientService.signUp(patientSignupDTO);
        return new ResponseEntity(patient, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PatientLoginDTO patientLoginDTO){
        try{
            Patient patient = patientService.login(patientLoginDTO);
            return new ResponseEntity(patient, HttpStatus.OK);
        }catch (WrongCredentials wrongCredentials){
            return new ResponseEntity(new GeneralMessageDTO(wrongCredentials.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(PatientDoesNotExistException patientDoesNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(patientDoesNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
