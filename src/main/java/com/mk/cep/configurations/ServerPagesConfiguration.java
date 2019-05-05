package com.mk.cep.configurations;


//import com.mk.cep.utils.EntityToIdConverter;
//import com.mk.cep.utils.IdToEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;

@Configuration
class ServerPagesConfiguration extends WebMvcConfigurationSupport {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/"};

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

//    @Bean
//    protected IdToEntityConverter idToEntityConverter() {
//        return new IdToEntityConverter();
//    }
//
//    @Bean
//    protected EntityToIdConverter entityToIdConverter() {
//        return new EntityToIdConverter();
//    }
//
//    @PostConstruct
//    public void addConverters() {
//        mvcConversionService().addConverter(idToEntityConverter());
//        mvcConversionService().addConverter(entityToIdConverter());
//    }
}


