package game.backlog.service;

import game.backlog.model.User;
import game.backlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {


    @Autowired
    private UserRepository userRepository;


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
