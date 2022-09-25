package br.com.precatorio.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ClienteConfiguration {

    @Bean
    public HttpClient cliente(){
        return HttpClient.newHttpClient();
    }
}
