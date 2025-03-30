package game.backlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/register")
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
    }

    @GetMapping("/users")
    public  @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
