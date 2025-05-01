package game.backlog.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
public class GameController {

    @Autowired
    private Environment environment;



    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchGames(@RequestParam(name = "query") String query) {
        String url = "https://www.giantbomb.com/api/search/?api_key=" + environment.getProperty("api.key") + "&format=json&query=" + query + "&resources=game";

        RestClient restClient = RestClient.create();
        ResponseEntity<String> response = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(String.class);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonResponse = objectMapper.readValue(response.getBody(), Map.class);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to parse JSON"));
        }
/*        return ResponseEntity.ok()
                .body(result.getBody());*/
    }
    @GetMapping("/game/{guid}")
    public ResponseEntity<Map<String, Object>> searchGame(@PathVariable(name = "guid") String guid) {
        String url = "https://www.giantbomb.com/api/game/" + guid + "/?api_key=" + environment.getProperty("api.key") + "&format=json";
        System.out.println(url);
        RestClient restClient = RestClient.create();

        ResponseEntity<String> response = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonResponse = objectMapper.readValue(response.getBody(), Map.class);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to parse JSON"));
        }
    }


}
