package hu.progmasters.exceptionhandler;

import lombok.Getter;

@Getter
public class AccountNotFoundWithIdException extends RuntimeException {

    private final Long id;

    public AccountNotFoundWithIdException(Long id) {
        this.id = id;
    }
}
