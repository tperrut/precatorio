package br.com.precatorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PrecatorioApplication {
    private static final Logger log = LoggerFactory.getLogger(PrecatorioApplication.class);

    public static void main(String[] args) {
        log.info("Vamo q vamo com Thiagão do GONGOLO!!!!!!!");
        SpringApplication.run(PrecatorioApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/*").allowedOrigins("https://precatorio-app.herokuapp.com");
            }
        };
    }

}
