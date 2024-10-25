package hu.progmasters.service;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.User;
import hu.progmasters.dto.outgoing.AccountDetails;
import hu.progmasters.exceptionhandler.AccountWithThisIdNotFoundException;
import hu.progmasters.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper, UserService userService) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public AccountDetails saveAccount(Long userId) {
        Account account = accountRepository.getAccountByUserId(userId)
                .orElseThrow(() -> new AccountWithThisIdNotFoundException(userId));
        User user = userService.findById(userId);
        account.setUser(user);
        accountRepository.save(account);
        return modelMapper.map(account, AccountDetails.class);
    }


}
