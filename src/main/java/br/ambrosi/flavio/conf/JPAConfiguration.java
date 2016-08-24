package br.ambrosi.flavio.conf;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties("oracle")
public class JPAConfiguration {

	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String url;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Bean
	DataSource dataSource() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setURL(url);
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}

}
