package main.exceptions;

public class XmlValidationException extends Exception {

    private static final String VALIDATION_EXCEPTION = "validation.exception";

    private static final String FILE_IO_EXCEPTION = "file.io.exception";

    public XmlValidationException(String message) {
        super(message);
    }

    public XmlValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static XmlValidationException xmlContentValidationException(Throwable cause) {
        return new XmlValidationException(VALIDATION_EXCEPTION, cause);
    }

    public static XmlValidationException inputFileIoException(Throwable cause) {
        return new XmlValidationException(FILE_IO_EXCEPTION, cause);
    }
}
