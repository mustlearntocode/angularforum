package net.mv.forum.config;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="net.mv.forum")
@EntityScan(basePackages="net.mv.forum")
public class DbConfig {
	
	@Bean(name="dataSource")
	public DataSource dataSource(){
//		DataSource dataSource = null;
//		
//		String host = "localhost";
//		String port = "5432";
//		String username= "postgres";
//		String password = "admin";
//		
//		String url = "jdbc:postgresql://"+host+":"+port+"/postgres";
//		
//		dataSource = new SimpleDriverDataSource(new org.postgresql.Driver(), url, username, password);
//		
//		return dataSource;
        String username = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");
        String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
        String databaseName = System.getenv("OPENSHIFT_APP_NAME");
        String url = "jdbc:postgresql://" + host + ":" + port + "/"+databaseName;
       // System.err.println("URL: " + url);;
		SimpleDriverDataSource ds = new SimpleDriverDataSource(new org.postgresql.Driver(),
				url, username, password);
		return ds;
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactory.setDataSource(dataSource());
		
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter
			= new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setShowSql(false);
		
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);

		return entityManagerFactory;
	}
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(){
		return new JpaTransactionManager();
	}

}
