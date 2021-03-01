package net.codejava.trajetoria_cidadao.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import net.codejava.trajetoria_cidadao.dao.DimSubprefeiturasDAO;
import net.codejava.trajetoria_cidadao.dao.DimSubprefeiturasDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.codejava.trajetoria_cidadao")
public class SpringMvcConfig implements WebMvcConfigurer{
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://10.20.3.6:3306/dw_covs");
		dataSource.setUsername("victordesgm");
		dataSource.setPassword("covs123");
		
		return dataSource;
	}
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Bean
	public DimSubprefeiturasDAO getContactDAO() {
		return new DimSubprefeiturasDAOImpl(getDataSource());
		
	}
}
