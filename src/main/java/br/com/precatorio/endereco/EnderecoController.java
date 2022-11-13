package br.com.precatorio.endereco;

import br.com.precatorio.system.exception.ApiViaCepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @GetMapping(path = "/endereco/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String buscarCep(@PathVariable String cep) {
        String endereco;
        try {
            endereco = service.buscarEnderecoPorCep(cep);
        } catch (ApiViaCepException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.name();
        }

        return endereco;
    }
}
