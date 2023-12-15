package creacademy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageUtils {

    @Autowired
    @Qualifier("microserviceMessageSource")
    private MessageSource messageSource;

    public String getMessage(String key) {
        return getMessage(key, "");
    }

    public String getMessage(String key, Object... params) {
        List<String> paramStrs = new ArrayList<>();
        for (Object param : params) {
            String value = String.valueOf(param);
            if (param instanceof DefaultMessageSourceResolvable) {
                value = ((DefaultMessageSourceResolvable) param).getDefaultMessage();
            }
            paramStrs.add(value);
        }
        return messageSource.getMessage(key, paramStrs.toArray(), LocaleContextHolder.getLocale());
    }

    public String getMessage(FieldError field) {
        return messageSource.getMessage(field, LocaleContextHolder.getLocale());
    }
}
