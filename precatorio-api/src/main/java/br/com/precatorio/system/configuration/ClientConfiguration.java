package br.com.precatorio.system.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ClientConfiguration {

    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }
}
