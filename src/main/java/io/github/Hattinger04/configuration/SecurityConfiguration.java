package io.github.Hattinger04.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/files/**")
	    .setCachePeriod(0)
	    .resourceChain(true)
	    .addResolver(new PathResourceResolver());
    }
    
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO: delete not needed origins when publising 
		registry.addMapping("/**").allowedOrigins("https://localhost", "https://192.168.0.42", "https://93.82.91.106", "https://192.168.31.65", "https://192.168.31.64").allowedMethods("GET", "POST", "OPTIONS")
		.allowCredentials(true).allowedHeaders("*").exposedHeaders("set-cookie").maxAge(3600); 
	}
	
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    String hierarchy = "ADMIN > DEV \n DEV > TEACHER \n TEACHER > USER";
	    roleHierarchy.setHierarchy(hierarchy);
	    return roleHierarchy;
	}

}
