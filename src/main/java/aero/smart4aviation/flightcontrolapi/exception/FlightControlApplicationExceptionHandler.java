package aero.smart4aviation.flightcontrolapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class FlightControlApplicationExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, UnrecognizedWeightUnitException.class})
    public ResponseEntity<?> handleException(RuntimeException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(exception.getMessage(), status);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception exception, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String responseMessage = "Unexpected error occurred" + exception.getMessage();
        return new ResponseEntity<>(responseMessage, status);
    }

}