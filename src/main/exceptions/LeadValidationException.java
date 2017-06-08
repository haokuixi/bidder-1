package main.exceptions;

public class LeadValidationException extends Exception {

    private static final String VALIDATION_EXCEPTION = "validation.dealresult.lead.wrong";

    public LeadValidationException(String message) {
        super(message);
    }

    public LeadValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static LeadValidationException leadValidationException() {
        return new LeadValidationException(VALIDATION_EXCEPTION, new Throwable());
    }

}
