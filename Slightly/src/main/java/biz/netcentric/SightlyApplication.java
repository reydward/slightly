package biz.netcentric;

import biz.netcentric.servlet.NTCFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SightlyApplication {

	@Bean
	public FilterRegistrationBean myFilterRegistration() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new NTCFilter());
	    registration.addUrlPatterns("*.nct");
	    
	    return registration;
	}
        
        public static void main(String[] args) {
		SpringApplication.run(SightlyApplication.class, args);
	}
}
