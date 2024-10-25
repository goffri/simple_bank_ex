package hu.progmasters.exceptionhandler;

import lombok.Getter;

@Getter
public class AccountWithThisUserIdNotFoundException extends RuntimeException {

    private final Long userId;

    public AccountWithThisUserIdNotFoundException(Long userId) {
        this.userId = userId;
    }
}
