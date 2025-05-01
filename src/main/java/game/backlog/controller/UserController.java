package game.backlog.controller;

import game.backlog.model.AuthenticationRequest;
import game.backlog.model.AuthenticationResponse;
import game.backlog.model.User;
import game.backlog.repository.UserRepository;
import game.backlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public @ResponseBody String addNewUser(@RequestParam String name
            , @RequestParam String email
            , @RequestParam String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return "Saved";
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


}
