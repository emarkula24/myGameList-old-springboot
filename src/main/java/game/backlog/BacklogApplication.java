package game.backlog;
import org.apache.coyote.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@SpringBootApplication
@RestController
public class BacklogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BacklogApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	private static final String API_KEY = "9329acb5599a035b68fd6bdea694cce3d82a7063"; // Replace with your actual API key
	private static final String BASE_URL = "http://www.giantbomb.com/api/search/";

	@GetMapping("/search")
	public ResponseEntity<String> searchGame(@RequestParam String query) {
		String url = "http://www.giantbomb.com/api/search/?api_key=9329acb5599a035b68fd6bdea694cce3d82a7063&format=json&query=" + query + "&resources=game";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		System.out.println(response);
		return response;
	}
}
