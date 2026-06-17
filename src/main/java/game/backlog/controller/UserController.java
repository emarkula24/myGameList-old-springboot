package game.backlog.controller;

import game.backlog.model.AuthenticationResponse;
import game.backlog.model.User;
import game.backlog.repository.UserRepository;
import game.backlog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, Environment environment, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(path = "/register")
    public @ResponseBody String addNewUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return "Saved";
    }


    @PostMapping("/login")
    public ResponseEntity <String> LoginUser(@RequestParam String username
            , @RequestParam String password) {

        User user = userRepository.findByUsername(username);
        if (user.getUsername() == null) {
            return ResponseEntity.status(401).body("User does not exist");
        }
        String accessToken = jwtUtil.createToken(username);
        return ResponseEntity.status(200).body(accessToken);

    }
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setUsername(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

}
