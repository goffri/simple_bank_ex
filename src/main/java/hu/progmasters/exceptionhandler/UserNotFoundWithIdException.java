package hu.progmasters.exceptionhandler;

import lombok.Getter;

@Getter
public class UserNotFoundWithIdException extends RuntimeException {

    private final Long userId;

    public UserNotFoundWithIdException(Long userId) {
        this.userId = userId;
    }
}
