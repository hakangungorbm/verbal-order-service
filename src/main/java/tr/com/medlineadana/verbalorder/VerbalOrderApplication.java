package tr.com.medlineadana.verbalorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//packaging = war, sonra deploy ettiğimiz yer External Apache Tomcat,  Angular → backend çağrılarında CORS ayarı yapılmalı ve SpringBootServletInitializer, giriş kapısı olarak kullanılmalı. External Tomcat’e “bu benim entry point’im” der
//JAR + embedded Tomcat → SpringBootServletInitializer gerekmez, WAR + external Tomcat → SpringBootServletInitializer ZORUNLU

@SpringBootApplication
public class VerbalOrderApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VerbalOrderApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(VerbalOrderApplication.class, args);
	}
}