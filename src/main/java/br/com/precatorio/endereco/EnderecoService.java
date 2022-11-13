package br.com.precatorio.endereco;

import br.com.precatorio.system.exception.ApiViaCepException;

public interface EnderecoService {
    String buscarEnderecoPorCep(String cep) throws ApiViaCepException;
}
