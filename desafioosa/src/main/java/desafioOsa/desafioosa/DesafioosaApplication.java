package desafioOsa.desafioosa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Your API Title", version = "1.0", description = "Your API description"))
public class DesafioosaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioosaApplication.class, args);
	}

}
 