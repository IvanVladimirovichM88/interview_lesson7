package ru.geekbrains.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan("ru.geekbrains")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        ViewControllerRegistration viewControllerRegistry = registry.addViewController("/");
        viewControllerRegistry.setViewName("/index");
    }
}
