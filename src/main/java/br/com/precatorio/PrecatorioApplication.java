package br.com.precatorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrecatorioApplication {
    private static final Logger log = LoggerFactory.getLogger(PrecatorioApplication.class);

    public static void main(String[] args) {
        log.info("Vamo q vamo com Thiagão do GONGOLO!!!!!!!");
        SpringApplication.run(PrecatorioApplication.class, args);
    }

}
