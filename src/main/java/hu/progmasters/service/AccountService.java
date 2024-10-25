package hu.progmasters.service;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.AccountNumberGenerator;
import hu.progmasters.domain.Transaction;
import hu.progmasters.domain.User;
import hu.progmasters.dto.outgoing.AccountDetails;
import hu.progmasters.dto.outgoing.AccountListItem;
import hu.progmasters.dto.outgoing.TransactionListItem;
import hu.progmasters.exceptionhandler.AccountNotFoundWithIdException;
import hu.progmasters.exceptionhandler.AccountWithThisUserIdNotFoundException;
import hu.progmasters.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    @Lazy
    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper, UserService userService, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public AccountDetails saveAccount(Long userId) {
        Account account = accountRepository.getAccountByUserId(userId)
                .orElseThrow(() -> new AccountWithThisUserIdNotFoundException(userId));
        User user = userService.findById(userId);
        account.setUser(user);
        accountRepository.save(account);
        return modelMapper.map(account, AccountDetails.class);
    }


    public AccountListItem getAccount(Long accountId) {
        Account account = findById(accountId);
        return modelMapper.map(account, AccountListItem.class);
    }

    public Account findById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundWithIdException(accountId));
    }


    public AccountListItem copyAccount(Long id) {
        Account account = findById(id);
        Account copyOfAccount = modelMapper.map(account, Account.class);
        List<Transaction> copyTransactions = modelMapper.map(account.getTransactions(), new TypeToken<List<Transaction>>() {}.getType());
        copyOfAccount.setTransactions(copyTransactions);
        copyOfAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
        transactionService.saveTransactionList(copyTransactions);
        accountRepository.save(copyOfAccount);
        return modelMapper.map(copyOfAccount, AccountListItem.class);
    }
}
