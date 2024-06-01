package org.example.webapitableau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebapitableauApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebapitableauApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebapitableauApplication.class);
    }

}
