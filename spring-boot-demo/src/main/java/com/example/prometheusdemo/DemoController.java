package com.example.prometheusdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DemoController {

    public DemoController() {
    }

    @GetMapping(value = "/hello/{name}")
    public ResponseEntity<DemoResponse> hello(@PathVariable String name) {
        if ("error".equalsIgnoreCase(name)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemoResponse("Opps!"));
        } else if ("unknown".equalsIgnoreCase(name)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DemoResponse("Not here"));
        }
        return ResponseEntity.ok(new DemoResponse("Hello " + name));
    }

    @GetMapping(value = "/lorem/{paragraphCount}", produces = TEXT_HTML_VALUE)
    public ResponseEntity<String> lorem(@PathVariable int paragraphCount) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://loripsum.net/api/" + paragraphCount))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 ) {
            return ResponseEntity.ok(response.body());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.body());
        }
    }

    static class DemoResponse {
        public final String message;

        DemoResponse(String message) {
            this.message = message;
        }
    }

}
