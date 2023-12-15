package creacademy.exception.handler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import creacademy.exception.InternalServerErrorException;
import creacademy.exception.NotFoundException;
import creacademy.exception.baseexception.HttpException;
import creacademy.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BaseExceptionHandler {
    @Value("${microservice.error.prefix}")
    String prefix;

    public MessageUtils messageUtil;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        if (ex instanceof HttpException) {
            return handleHttpException((HttpException) ex);
        }
        return handleExceptionInternal(ex);
    }
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex) {
//        debug(ex);
        return handleHttpException(new InternalServerErrorException());
    }
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return handleHttpException(new NotFoundException());
    }
    protected ResponseEntity<Object> handleHttpException(HttpException ex) {
        if (ex.getCode().startsWith(this.prefix)) {
            this.prefix = "";
        }
        if (StringUtils.isNotEmpty(ex.getMessage())) {
            return new ResponseEntity<>(ex.getErrorMessage(this.prefix), ex.getStatusCode());
        }
        if (HttpStatus.NOT_ACCEPTABLE.equals(ex.getStatusCode())) {
            var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(ex.getErrorMessage(this.prefix, this.messageUtil), headers, ex.getStatusCode());
        }
        return new ResponseEntity<>(ex.getErrorMessage(this.prefix, this.messageUtil), ex.getStatusCode());
    }
    private void debug(Exception ex) {
        ex.printStackTrace();
    }
}
