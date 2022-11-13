package br.com.precatorio.cliente;

import br.com.precatorio.cliente.conjugue.Conjugue;
import br.com.precatorio.cliente.conjugue.ConjugueRepository;
import br.com.precatorio.contrato.Contrato;
import br.com.precatorio.contrato.ContratoRepository;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ConjugueRepository conjugueRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public Cliente salvar(@Valid ClienteDto dto) {
        Cliente cliente = ClienteDto.convertDtoToCliente(dto);

        salvarEndereco(cliente);

        clienteRepository.save(cliente);

        if (Objects.nonNull(cliente.getConjugue())) {
            cliente.getConjugue().setCliente(cliente);
            salvarConjugue(cliente.getConjugue());
        }

        if (cliente.getContratos().stream().findFirst().isPresent())
            salvarContrato(cliente);

        return cliente;
    }

    @Override
    public void update(ClienteDto dto, Cliente cliente) {
        Cliente clienteConverted = ClienteDto.convertDtoToCliente(dto);
        clienteConverted.setId(cliente.getId());
        salvarEndereco(clienteConverted);

        clienteRepository.save(clienteConverted);

        if (Objects.nonNull(clienteConverted.getConjugue())) {
            clienteConverted.getConjugue().setCliente(clienteConverted);
            clienteConverted.getConjugue().setId(cliente.getConjugue().getId());
            salvarConjugue(clienteConverted.getConjugue());
        }

        if (clienteConverted.getContratos().stream().findFirst().isPresent())
            salvarContrato(clienteConverted);
    }

    private void salvarContrato(Cliente cliente) {
        Contrato contrato = cliente.getContratos().stream().findFirst().get();
        contrato.setCliente(cliente);
        contratoRepository.save(contrato);
    }

    private void salvarEndereco(Cliente cliente) {
        if (Objects.nonNull(cliente.getConjugue()))
            enderecoRepository.save(cliente.getConjugue().getEndereco());

        enderecoRepository.save(cliente.getEndereco());
    }

    private Endereco salvarEnderecoConjugue(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    private void salvarConjugue(Conjugue cjg) {
        Endereco adress = salvarEnderecoConjugue(cjg.getEndereco());
        cjg.setEndereco(adress);
        conjugueRepository.save(cjg);
    }

    public Optional<Cliente> findById(Long id) {

        return clienteRepository.findById(id);
    }


}
