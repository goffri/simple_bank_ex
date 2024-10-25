package hu.progmasters.exceptionhandler;

import lombok.Data;

@Data
public class ValidationError {

    private String field;
    private String errorMessage;

    public ValidationError(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }
}
