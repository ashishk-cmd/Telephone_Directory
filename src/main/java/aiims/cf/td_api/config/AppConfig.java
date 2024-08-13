package aiims.cf.td_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	 @Bean
	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	        return builder.build();
	    }
	 
	 @Bean
	    public RestTemplate restTemplate2() {
	        return new RestTemplate();
	    }
	 
	 @Bean
	 public ModelMapper getModalMapper() {
		 return new ModelMapper();
	 }
}
