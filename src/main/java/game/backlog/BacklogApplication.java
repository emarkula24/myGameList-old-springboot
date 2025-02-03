package game.backlog;
import org.apache.coyote.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
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

	private static final String API_KEY = "9329acb5599a035b68fd6bdea694cce3d82a7063"; // Replace with your actual API key
	private static final String BASE_URL = "http://www.giantbomb.com/api/search/";

	@GetMapping("/search")
	public ResponseEntity<String> searchGameClient(@RequestParam String query) {
		String url = "http://www.giantbomb.com/api/search/?api_key=9329acb5599a035b68fd6bdea694cce3d82a7063&format=json&query=" + query + "&resources=game";

		RestClient defaultClient = RestClient.create();
		ResponseEntity<String> result = defaultClient.get()
				.uri("http://www.giantbomb.com/api/search/?api_key=9329acb5599a035b68fd6bdea694cce3d82a7063&format=json&query={quey}&resources=game", query)
				.retrieve()
				.toEntity(String.class);
		System.out.println("Response status: " + result.getStatusCode());
		System.out.println("Response headers: " + result.getHeaders());
		System.out.println("Contents: " + result.getBody());
		return result;
	}
}
