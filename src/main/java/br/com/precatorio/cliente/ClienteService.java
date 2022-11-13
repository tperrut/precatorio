package br.com.precatorio.cliente;

public interface ClienteService<T> {

    T salvar(ClienteDto dto);

    void update(ClienteDto dto, T cliente);

}
