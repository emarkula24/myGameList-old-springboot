package game.backlog.service;

import game.backlog.model.User;
import game.backlog.repository.UserRepository;

public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
