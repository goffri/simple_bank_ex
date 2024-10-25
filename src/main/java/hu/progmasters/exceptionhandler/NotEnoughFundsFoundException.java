package hu.progmasters.exceptionhandler;

import lombok.Getter;

@Getter
public class NotEnoughFundsFoundException extends RuntimeException {

    private final Long id;

    public NotEnoughFundsFoundException(Long id) {
        this.id = id;
    }
}
