package hu.progmasters.controller;

import hu.progmasters.domain.User;
import hu.progmasters.dto.incoming.UserCommand;
import hu.progmasters.dto.outgoing.UserDetails;
import hu.progmasters.dto.outgoing.UserDetailsWithAccounts;
import hu.progmasters.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDetails> addUser(@Valid @RequestBody UserCommand command) {
        log.info("HTTP Post Dwarf: {}", command);
        UserDetails response = userService.saveUser(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsWithAccounts> getUser(@PathVariable Long id) {
        log.info("HTTP Get User: {}", id);
        UserDetailsWithAccounts response = userService.getUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetails> updateUser(@PathVariable Long id, @Valid @RequestBody UserCommand command) {
        log.info("HTTP Put User: {}", id);
        UserDetails response = userService.update(id, command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
