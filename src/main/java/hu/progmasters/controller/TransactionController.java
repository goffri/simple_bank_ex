package hu.progmasters.controller;

import hu.progmasters.dto.incoming.TransactionCommand;
import hu.progmasters.dto.outgoing.TransactionDetails;
import hu.progmasters.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/reference/receiver/{receiverId}")
    public ResponseEntity<TransactionDetails> addNewTransaction(@PathVariable("receiverId") Long receiverId, @RequestBody TransactionCommand command) {
        log.info("HTTP Post Transaction: {}", receiverId);
        TransactionDetails response = transactionService.addNewTransaction(receiverId, command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/reference/receiver/{receiverId}/sender/{senderId}")
    public ResponseEntity<List<TransactionDetails>> addNewTransaction(@PathVariable("receiverId") Long receiverId,
                                                                      @PathVariable("senderId") Long senderId,
                                                                      @RequestBody TransactionCommand command) {
        log.info("HTTP Post Transaction: {}", receiverId);
        List<TransactionDetails> response = transactionService.addNewTransfer(receiverId, senderId, command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
