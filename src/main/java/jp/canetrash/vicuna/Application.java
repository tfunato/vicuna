package jp.canetrash.vicuna;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * vicuna application
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	private static final Log logger = LogFactory.getLog(Application.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(
				Application.class, args);
		// initialize
		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		DatabaseMetaData metaData = jdbcTemplate.getDataSource()
				.getConnection().getMetaData();
		ResultSet rs = metaData.getTables(null, null, "DAMAGE_REPORT_MAIL",
				null);
		if (!rs.next()) {
			logger.info("Initializing Database....");
			jdbcTemplate.execute(Const.INIT_TABLE);
			logger.info("Initializing Done");
		}
	}
}
