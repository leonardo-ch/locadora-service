package com;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    //mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeArtifactId=camel-archetype-spring-boot

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<CamelHttpTransportServlet> servlet = new ServletRegistrationBean<CamelHttpTransportServlet>(new CamelHttpTransportServlet(), "/api-locadora/*");
        servlet.setName("LocadoraService");
        return servlet;
    }
}
