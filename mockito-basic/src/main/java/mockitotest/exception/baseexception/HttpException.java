package mockitotest.exception.baseexception;

import lombok.Getter;
import mockitotest.constants.CommonMessages;
import mockitotest.exception.error.ErrorDetail;
import mockitotest.exception.error.ErrorMessage;
import mockitotest.utils.MessageUtils;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
public class HttpException extends RuntimeException {
    private HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    private String code = CommonMessages.BAD_REQUEST;
    private Object response;
    private String field;

    @SuppressWarnings("all")
    private Object[] args = new Object[]{};

    public HttpException(HttpStatus statusCode, String code, Object... args) {
        this.statusCode = statusCode;
        this.code = code;
        this.args = args;
    }

    public HttpException(HttpStatus statusCode, String code, String field, Object... args) {
        this.statusCode = statusCode;
        this.code = code;
        this.field = field;
        this.args = args;
    }

    public HttpException(HttpStatus statusCode, String code, String message, String field) {
        super(message);
        this.statusCode = statusCode;
        this.code = code;
        this.field = field;
    }

    public HttpException(HttpStatus statusCode, String code, String message) {
        super(message);
        this.statusCode = statusCode;
        this.code = code;
    }
    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }
    public Object getResponse() {
        if (response == null) {
            response = new ErrorResponse(code, getMessage());
        }
        return response;
    }

    public HttpException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorMessage getErrorMessage(String prefix, MessageUtils messageUtils) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(this.statusCode);
        String message = null;
        if(Objects.nonNull(messageUtils)) {
            message = messageUtils.getMessage(code, args);
        }
        errorMessage.addErrorDetail(this.buildErrorDetail(message, prefix.concat(code)));
        return errorMessage;
    }

    public ErrorMessage getErrorMessage(String prefix) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(this.statusCode);
        errorMessage.addErrorDetail(this.buildErrorDetail(this.getMessage(), prefix.concat(code)));
        return errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(this.statusCode);
        errorMessage.addErrorDetail(this.buildErrorDetail(this.getMessage(), this.code));
        return errorMessage;
    }

    private ErrorDetail buildErrorDetail(String message, String code) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setMessage(message);
        errorDetail.setCode(code);
        errorDetail.setField(this.field);
        return errorDetail;
    }



    @Getter
    public static class ErrorResponse {
        private final String code;
        private final String message;

        ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
