package tr.com.medlineadana.verbalorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VerbalOrderApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VerbalOrderApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(VerbalOrderApplication.class, args);
	}
}
