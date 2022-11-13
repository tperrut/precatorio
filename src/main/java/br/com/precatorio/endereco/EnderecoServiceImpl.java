package br.com.precatorio.endereco;

import br.com.precatorio.system.exception.ApiViaCepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    HttpClient client;

    @Override
    public String buscarEnderecoPorCep(String cep) throws ApiViaCepException {
        HttpRequest request;
        HttpResponse<String> response;
        try {
            request = prepareRequest(cep);

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException e) {
            throw new ApiViaCepException(e, "[Erro] - Api ViaCep!");
        } catch (IOException e) {
            throw new ApiViaCepException(e, "[Erro] - Api ViaCep!");
        } catch (InterruptedException e) {
            throw new ApiViaCepException(e, "[Erro] - Api ViaCep!");
        }
        return response.body();
    }

    private HttpRequest prepareRequest(String cep) throws URISyntaxException {
        String uri = MessageFormat.format("https://viacep.com.br/ws/{0}/json/", cep);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
        return request;
    }
}
