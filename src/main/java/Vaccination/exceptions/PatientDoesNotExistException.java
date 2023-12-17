package Vaccination.exceptions;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String mssg){
        super(mssg);
    }
}