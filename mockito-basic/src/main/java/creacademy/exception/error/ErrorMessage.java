package creacademy.exception.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String EMPTY_MESSAGE = "Empty message";
    @JsonIgnore
    private String message;
    @JsonIgnore
    private HttpStatus status;
    private List<ErrorDetail> errors;

    public ErrorMessage() {
        this.status = HttpStatus.BAD_REQUEST;
        this.errors = new ArrayList<>();
    }

    public ErrorMessage(String message) {
        this.status = HttpStatus.BAD_REQUEST;
        this.errors = new ArrayList<>();
        this.message = message;
    }

    public ErrorMessage(String message, List<ErrorDetail> errors) {
        this.status = HttpStatus.BAD_REQUEST;
        this.errors = new ArrayList<>();
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        if (StringUtils.isBlank(this.message)) {
            this.message = "Empty message";
        }

        return this.message;
    }

    public void addErrorDetail(ErrorDetail errorDetail) {
        this.errors.add(errorDetail);
    }

    @Generated
    public HttpStatus getStatus() {
        return this.status;
    }

    @Generated
    public List<ErrorDetail> getErrors() {
        return this.errors;
    }

    @Generated
    public void setMessage(String message) {
        this.message = message;
    }

    @Generated
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Generated
    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ErrorMessage)) {
            return false;
        } else {
            ErrorMessage other = (ErrorMessage)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label47;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label47;
                    }

                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$errors = this.getErrors();
                Object other$errors = other.getErrors();
                if (this$errors == null) {
                    return other$errors == null;
                } else return this$errors.equals(other$errors);
            }
        }
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ErrorMessage;
    }

    @Generated
    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $errors = this.getErrors();
        result = result * 59 + ($errors == null ? 43 : $errors.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getMessage();
        return "ErrorMessage(message=" + var10000 + ", status=" + this.getStatus() + ", errors=" + this.getErrors() + ")";
    }
}

