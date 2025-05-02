package game.backlog.controller;

import game.backlog.model.AuthenticationRequest;
import game.backlog.model.AuthenticationResponse;
import game.backlog.model.User;
import game.backlog.repository.UserRepository;
import game.backlog.service.UserService;
import game.backlog.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment environment;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/register")
    public @ResponseBody String addNewUser(@RequestParam String username
            , @RequestParam String email
            , @RequestParam String password) {

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return "Saved";
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity <String> LoginUser(@RequestParam String username
            , @RequestParam String password) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(500).body("User does not exist");
        }
        String accessToken = jwtUtil.createToken(username);
        return ResponseEntity.status(400).body("User has been authenticated";



    }

}
