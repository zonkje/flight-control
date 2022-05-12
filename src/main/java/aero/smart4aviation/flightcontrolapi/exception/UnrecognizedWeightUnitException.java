package aero.smart4aviation.flightcontrolapi.exception;

public class UnrecognizedWeightUnitException extends RuntimeException {
    public UnrecognizedWeightUnitException(String unit) {
        super(String.format("Unit \"%s\" not recognized", unit));
    }
}
