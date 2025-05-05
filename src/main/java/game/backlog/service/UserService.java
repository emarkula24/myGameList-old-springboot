package game.backlog.service;

import game.backlog.model.User;
import game.backlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {


    @Autowired
    private UserRepository userRepository;


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
