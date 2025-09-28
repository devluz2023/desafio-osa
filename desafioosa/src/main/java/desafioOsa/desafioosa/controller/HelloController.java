package desafioOsa.desafioosa.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}