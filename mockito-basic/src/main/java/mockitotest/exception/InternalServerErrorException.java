package mockitotest.exception;

import mockitotest.constants.CommonMessages;
import mockitotest.exception.baseexception.HttpException;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpException {

    private static final long serialVersionUID = 1L;

    public InternalServerErrorException() {
        this(CommonMessages.INTERNAL_ERROR);
    }
    public InternalServerErrorException(String code) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code);
    }
    public InternalServerErrorException(String code, Object... args) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, args);
    }
    public InternalServerErrorException(String message, String code) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, message);
    }
    public InternalServerErrorException(String code, String field, Object... args) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, field, args);
    }

    public InternalServerErrorException(String code, String message, String field) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, message, field);
    }
}
