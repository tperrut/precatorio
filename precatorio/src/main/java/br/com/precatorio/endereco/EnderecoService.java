package br.com.precatorio.endereco;

import br.com.precatorio.system.exception.ApiViaCepException;
import org.springframework.stereotype.Service;

public interface EnderecoService {
    String buscarEnderecoPorCep(String cep) throws ApiViaCepException;
}
