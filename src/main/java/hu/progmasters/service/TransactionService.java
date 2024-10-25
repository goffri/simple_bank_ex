package hu.progmasters.service;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.Transaction;
import hu.progmasters.domain.TransactionType;
import hu.progmasters.dto.incoming.TransactionCommand;
import hu.progmasters.dto.outgoing.TransactionDetails;
import hu.progmasters.exceptionhandler.NotEnoughFundsFoundException;
import hu.progmasters.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }


    public TransactionDetails addNewTransaction(Long receiverId, TransactionCommand command) {
        Account account = accountService.findById(receiverId);
        Transaction transaction = modelMapper.map(command, Transaction.class);
        transaction.setAccount(account);
        depositFunds(transaction, account);
        withdrawFunds(receiverId, transaction, account);
        transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionDetails.class);
    }


    public List<TransactionDetails> addNewTransfer(Long receiverId, Long senderId, TransactionCommand command) {
        Account receiverAccount = accountService.findById(receiverId);
        Transaction receieverTransaction = modelMapper.map(command, Transaction.class);
        receieverTransaction.setAccount(receiverAccount);
        receieverTransaction.setTransactionType(TransactionType.DEPOSIT);
        depositFunds(receieverTransaction, receiverAccount);
        transactionRepository.save(receieverTransaction);

        Account senderAccount = accountService.findById(senderId);
        Transaction senderTransaction = modelMapper.map(command, Transaction.class);
        senderTransaction.setAccount(senderAccount);
        senderTransaction.setTransactionType(TransactionType.WITHDRAWAL);
        withdrawFunds(receiverId, senderTransaction, senderAccount);
        transactionRepository.save(senderTransaction);

        return List.of(modelMapper.map(receieverTransaction, TransactionDetails.class),
                modelMapper.map(senderTransaction, TransactionDetails.class));
    }


    private void withdrawFunds(Long receiverId, Transaction transaction, Account account) {
        if (transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
            if (transaction.getAccount().getBalance() < transaction.getAmount()) {
                throw new NotEnoughFundsFoundException(receiverId);
            }
            account.setBalance(account.getBalance() - transaction.getAmount());
        }
    }

    private void depositFunds(Transaction transaction, Account account) {
        if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
    }

    public void saveTransactionList(List<Transaction> copyTransactions) {
        transactionRepository.saveAll(copyTransactions);
    }
}
