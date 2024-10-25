package hu.progmasters.controller;

import hu.progmasters.dto.incoming.AccountCommand;
import hu.progmasters.dto.incoming.UserCommand;
import hu.progmasters.dto.outgoing.AccountDetails;
import hu.progmasters.dto.outgoing.AccountListItem;
import hu.progmasters.dto.outgoing.UserDetails;
import hu.progmasters.service.AccountService;
import hu.progmasters.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<AccountDetails> addAccount(@PathVariable("userId") Long userId) {
        log.info("HTTP Post Account: {}", userId);
        AccountDetails response = accountService.saveAccount(userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountListItem> getAccount(@PathVariable("accountId") Long accountId) {
        log.info("HTTP Get Account: {}", accountId);
        AccountListItem response = accountService.getAccount(accountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountListItem> copyAccount(@PathVariable("id") Long id) {
        log.info("HTTP Get another Account: {}", id);
        AccountListItem response = accountService.copyAccount(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
