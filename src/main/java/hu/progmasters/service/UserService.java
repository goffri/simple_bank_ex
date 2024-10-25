package hu.progmasters.service;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.User;
import hu.progmasters.dto.incoming.UserCommand;
import hu.progmasters.dto.outgoing.AccountListItem;
import hu.progmasters.dto.outgoing.UserDetails;
import hu.progmasters.dto.outgoing.UserDetailsWithAccounts;
import hu.progmasters.exceptionhandler.UserAlreadyExistsException;
import hu.progmasters.exceptionhandler.UserNotFoundWithIdException;
import hu.progmasters.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDetails saveUser(UserCommand command) {
        User user = userRepository.getUserByEmail(command.getEmail())
                .orElseThrow(() -> new UserAlreadyExistsException(command));
        userRepository.save(user);
        return modelMapper.map(user, UserDetails.class);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundWithIdException(userId));
    }

    public UserDetailsWithAccounts getUser(Long id) {
        User user = findById(id);
        UserDetailsWithAccounts outgoing = modelMapper.map(user, UserDetailsWithAccounts.class);
//        outgoing.setAccounts(modelMapper.map(user.getAccounts(), new TypeToken<List<AccountListItem>>() {}.getType()));
        return outgoing;
    }

    public UserDetails update(Long id, UserCommand command) {
        User user = findById(id);
        modelMapper.map(user, command);
        return modelMapper.map(user, UserDetails.class);
    }

}
