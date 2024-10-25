package hu.progmasters.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationError(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountWithThisIdNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleAccountNotFound(AccountWithThisIdNotFoundException exception) {
        return new ResponseEntity<>(
                List.of(new ValidationError("userId", "no account found with userId: " + exception.getUserId())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<List<ValidationError>> handleUserAlreadyExists(UserAlreadyExistsException exception) {
        return new ResponseEntity<>(
                List.of(new ValidationError("userName", "user with this email already found with name: "
                        + exception.getUserName() + " email: " + exception.getEmail())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundWithIdException.class)
    public ResponseEntity<List<ValidationError>> handleUserNotFound(UserNotFoundWithIdException exception) {
        return new ResponseEntity<>(
                List.of(new ValidationError("userId", "no user found with id: " + exception.getUserId())),
                HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(ItemNotFoundException.class)
//    public ResponseEntity<List<ValidationError>> handleItemNotFound(ItemNotFoundException exception) {
//        return new ResponseEntity<>(
//                List.of(new ValidationError("itemId", "no item found with id: " + exception.getItemId())),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MineIsAbandonedException.class)
//    public ResponseEntity<List<ValidationError>> handleMineIsAbandoned() {
//        return new ResponseEntity<>(
//                List.of(new ValidationError("mine", "The mine is abandoned. No one works here.")),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(NoRunesToBanException.class)
//    public ResponseEntity<List<ValidationError>> handleNoRunesToBan(NoRunesToBanException exception) {
//        return new ResponseEntity<>(
//                List.of(new ValidationError("noRunes", "No runes found for banning with name: "
//                        + exception.getRuneName())),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(NoRunesException.class)
//    public ResponseEntity<List<ValidationError>> handleNoRunes() {
//        return new ResponseEntity<>(
//                List.of(new ValidationError("noRunes", "No runes found.")),
//                HttpStatus.BAD_REQUEST);


}