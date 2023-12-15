package creacademy.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Generated;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail implements Serializable {
    private static final long serialVersionUID = -7046567216197159057L;
    private String code;
    private String message;

    private String field;

    public ErrorDetail(String prefix, String code, String message) {
        this.code = prefix != null ? prefix.concat(code) : code;
        this.message = message;
    }

    public ErrorDetail(String prefix, HttpStatus code, String message, String field) {
        this.code = prefix != null ? prefix.concat(code.name()) : code.name();
        this.message = message;
        this.field = field;
    }

    public ErrorDetail(String prefix, String code, String message, String field) {
        this.code = prefix != null ? prefix.concat(code) : code;
        this.message = message;
        this.field = field;
    }

    @Generated
    public String getCode() {
        return this.code;
    }

    @Generated
    public String getMessage() {
        return this.message;
    }

    @Generated
    public String getField() { return this.field; }


    @Generated
    public void setCode(String code) {
        this.code = code;
    }

    @Generated
    public void setMessage(String message) {
        this.message = message;
    }

    @Generated
    public void setField(String field) { this.field = field; }

    @Generated
    public ErrorDetail() {
    }

    @Generated
    public String toString() {
        return "ErrorDetail(code=" + this.getCode() + ", message=" + this.getMessage() + ", field=" + this.getField() + ")";
    }
}


