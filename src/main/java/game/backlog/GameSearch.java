package game.backlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@RestController
public class GameSearch {

    public static void main(String[] args) {
        SpringApplication.run(GameSearch.class, args);
    }

    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**").allowedOrigins("*");
            }
        };
    }
    @GetMapping("/search")
    public ResponseEntity<String> searchGame(@RequestParam(name = "query") String query) {
        String url = "https://www.giantbomb.com/api/search/?api_key=9329acb5599a035b68fd6bdea694cce3d82a7063&format=json&query=" + query + "&resources=game";

        RestClient restClient = RestClient.create();
        ResponseEntity<String> result = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(String.class);

        return ResponseEntity.ok()
                .body(result.getBody());
    }
}