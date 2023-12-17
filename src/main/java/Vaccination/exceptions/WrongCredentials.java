package Vaccination.exceptions;

public class WrongCredentials extends RuntimeException{
    public WrongCredentials(String mssg){
        super(mssg);
    }
}
