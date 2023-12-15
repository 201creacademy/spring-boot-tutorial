package creacademy.exception.handler;

import creacademy.utils.MessageUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler extends BaseExceptionHandler {
    public ExceptionHandler() {
    }
    public ExceptionHandler(MessageUtils messageUtil) {
        this.messageUtil = messageUtil;
    }
    public ExceptionHandler(MessageUtils messageUtil, String prefix) {
        this.messageUtil = messageUtil;
        this.prefix = prefix;
    }
}