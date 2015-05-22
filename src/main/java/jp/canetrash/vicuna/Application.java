package jp.canetrash.vicuna;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * vicuna application
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableAsync
public class Application extends SpringBootServletInitializer {

	private static final Log logger = LogFactory.getLog(Application.class);

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		logger.info("Welcome Vicuna Application!");
		SpringApplication.run(Application.class, args);
	}

}
