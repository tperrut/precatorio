package br.com.precatorio.cliente;

import br.com.precatorio.cliente.conjugue.Conjugue;
import br.com.precatorio.cliente.conjugue.ConjugueRepository;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ConjugueRepository conjugueRepository;

   public void salvar(Cliente cliente){
       salvarEndereco(cliente.getEndereco());
       salvarConjugue(cliente.getConjugue());
       clienteRepository.save(cliente);
   }

    private void salvarEndereco(Endereco endereco){
        enderecoRepository.save(endereco);
    }
    private void salvarConjugue(Conjugue cjg){
        conjugueRepository.save(cjg);
    }

}
