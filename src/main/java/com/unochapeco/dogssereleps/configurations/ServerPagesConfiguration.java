package com.unochapeco.dogssereleps.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
class ServerPagesConfiguration extends WebMvcConfigurationSupport {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/" };

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resources/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

}


