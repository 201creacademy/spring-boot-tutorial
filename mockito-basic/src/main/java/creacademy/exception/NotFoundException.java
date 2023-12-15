package creacademy.exception;

import creacademy.constants.CommonMessages;
import creacademy.exception.baseexception.HttpException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        this(CommonMessages.NOT_EXIST);
    }
    public NotFoundException(String code) {
        super(HttpStatus.NOT_FOUND, code);
    }
    public NotFoundException(String code, Object... args) {
        super(HttpStatus.NOT_FOUND, code, args);
    }
    public NotFoundException(String message, String code) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
    public NotFoundException(String code, String field, Object... args) {
        super(HttpStatus.NOT_FOUND, code, field, args);
    }

    public NotFoundException(String code, String message, String field) {
        super(HttpStatus.NOT_FOUND, code, message, field);
    }
}

