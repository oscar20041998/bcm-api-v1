package code88.oscar.bcm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @FileName: WebConfigSecurity.java
 * @since: 06/12/2020
 * */
@Configuration
public class WebConfigSecurity implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://coffee-management-fe-0498.herokuapp.com")
                .allowedMethods("GET, POST, DELETE")
                .allowCredentials(true);
    }
}
