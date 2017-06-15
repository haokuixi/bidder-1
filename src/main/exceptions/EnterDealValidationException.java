package main.exceptions;

public class EnterDealValidationException extends Exception {

    private static final String UNKNOWN_ERROR = "label.deal.unknownerror";
    private static final String WRONG_CONTENT = "label.deal.wrongcontent";

    public EnterDealValidationException(String message) {
        super(message);
    }

    public EnterDealValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static EnterDealValidationException unknownError(Throwable t) {
        return new EnterDealValidationException(UNKNOWN_ERROR, t);
    }

    public static EnterDealValidationException wrongContent(Throwable t) {
        return new EnterDealValidationException(WRONG_CONTENT, t);
    }

}