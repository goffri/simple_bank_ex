package hu.progmasters.exceptionhandler;

import hu.progmasters.dto.incoming.UserCommand;
import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private final String userName;
    private final String email;

    public UserAlreadyExistsException(UserCommand command) {
        this.userName = command.getName();
        this.email = command.getEmail();
    }
}
