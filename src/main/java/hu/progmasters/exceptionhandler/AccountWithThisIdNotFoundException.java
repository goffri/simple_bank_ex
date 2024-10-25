package hu.progmasters.exceptionhandler;

import lombok.Getter;

@Getter
public class AccountWithThisIdNotFoundException extends RuntimeException {

    private final Long userId;

    public AccountWithThisIdNotFoundException(Long userId) {
        this.userId = userId;
    }
}
