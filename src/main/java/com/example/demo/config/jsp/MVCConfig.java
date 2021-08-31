package com.example.demo.config.jsp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//명시적으로 해도 void 타입 jsp 가 못 알아 먹음


//@Configuration
//public class MVCConfig implements WebMvcConfigurer {
//    @Bean
//    public ViewResolver internalResourceViewResolver(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setSuffix("/WEB-INF/views/");
//        resolver.setPrefix(".jsp");
//        return resolver;
//    }
//}
