package app;

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

    @Bean
    public ServletRegistrationBean<CamelHttpTransportServlet> camelServletRegistrationBean() {
        ServletRegistrationBean<CamelHttpTransportServlet> registration = new ServletRegistrationBean<CamelHttpTransportServlet>(new CamelHttpTransportServlet(), "/api-locadora/*");
        registration.setName("CamelServlet");
        return registration;
    }
}
