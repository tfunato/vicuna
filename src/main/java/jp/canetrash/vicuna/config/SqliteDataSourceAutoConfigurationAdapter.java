package jp.canetrash.vicuna.config;

import javax.sql.DataSource;

import jp.canetrash.vicuna.Const;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author tfunato
 *
 */
@Configuration
@ConditionalOnClass({ DataSource.class })
@EnableConfigurationProperties(DataSourceProperties.class)
@Import({ DataSourcePoolMetadataProvidersConfiguration.class })
public class SqliteDataSourceAutoConfigurationAdapter extends
		DataSourceAutoConfiguration {

	@Autowired
	private DataSourceProperties properties;

	@Bean
	@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
	public DataSource dataSource() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.properties.getClassLoader())
				.driverClassName(this.properties.getDriverClassName())
				.url("jdbc:sqlite:" + Const.STORE_BASE_DIR + "/vicuna.db")
				.username(this.properties.getUsername())
				.password(this.properties.getPassword());
		return factory.build();
	}

}
