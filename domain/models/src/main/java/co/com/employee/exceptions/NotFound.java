package co.com.employee.exceptions;

public class NotFound extends RuntimeException{

    public NotFound(String message) {
        super(message);
    }

}