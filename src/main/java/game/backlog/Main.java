package game.backlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class Main {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}