package jp.canetrash.vicuna;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.JournalMode;
import org.sqlite.SQLiteConfig.SynchronousMode;
import org.sqlite.SQLiteDataSource;

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
		ConfigurableApplicationContext ctx = SpringApplication.run(
				Application.class, args);

		DataSource ds = ctx.getBean(DataSource.class);
		Connection con = ds.getConnection();
		System.out.println(con);
		System.out.println(ds);
		/*
		SQLiteDataSource sqliteDs = (SQLiteDataSource) ds;
		SQLiteConfig config = new SQLiteConfig();
		config.setJournalMode(JournalMode.MEMORY);
		config.setSynchronous(SynchronousMode.OFF);
		sqliteDs.setConfig(config);
		*/
	}

}
