package br.com.precatorio;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.cliente.ClienteRepository;
import br.com.precatorio.cliente.conjugue.Conjugue;
import br.com.precatorio.cliente.conjugue.ConjugueRepository;
import br.com.precatorio.contato.Contato;
import br.com.precatorio.contato.ContatoRepository;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.endereco.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PrecatorioApplicationTests {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ConjugueRepository conjugueRepository;

    @Test
    public void testFindById() {
        Cliente cliente = mountClienteForSave();
        clienteRepository.save(cliente);
        Cliente result = clienteRepository.findById(cliente.getId()).get();
        assertEquals(cliente.getId(), result.getId());
    }

    @Test
    public void testFindAllClientes() {
        Cliente cliente = mountClienteForSave();

        clienteRepository.save(cliente);

        List<Cliente> result = new ArrayList<>();
        clienteRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 1);
    }

    private Cliente mountClienteForSave() {
        Cliente cliente = getCliente();
        Endereco endereco = saveEndereco();
        cliente.setEndereco(endereco);
        cliente.setContato(saveContato());
        cliente.setConjugue(saveConjugue(endereco));
        return cliente;
    }

    @Test
    public void testSaveCliente() {
        Cliente cliente = mountClienteForSave();
        clienteRepository.save(cliente);
        Cliente found = clienteRepository.findById(cliente.getId()).get();
        assertEquals(cliente.getId(), found.getId());
    }

    @Test
    public void testDeleteByIdCliente() {
        Cliente cliente = mountClienteForSave();
        clienteRepository.save(cliente);
        clienteRepository.deleteById(cliente.getId());
        List<Cliente> result = new ArrayList<>();
        clienteRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }

    private Cliente getCliente() {
        Cliente cliente = Cliente.builder().rg("020.133.110-5").cpf("105.932.327-39").estadoCivil("Casado").build();
        return cliente;
    }

    private Endereco saveEndereco() {
        Endereco adress = getEndereco();
        return enderecoRepository.save(adress);
    }
    private Conjugue saveConjugue(Endereco adress) {
        Conjugue cjg = getConjugue();
        cjg.setEndereco(adress);
        return conjugueRepository.save(cjg);
    }

    private Contato saveContato() {
        Contato ctt = getContato();
        return contatoRepository.save(ctt);
    }

    private List<Contato> getContatos() {
        return Stream.of(
                Contato.builder().nome("Jack tequila").email("email@email.com").telefone("2198511-0076").build(),
                Contato.builder().nome("Jack Estripador").email("email@email.com").telefone("2198511-000").build(),
                Contato.builder().nome("Jhon Jhonson").email("email@email.com").telefone("2198511-8687").build(),
                Contato.builder().nome("Jay tequila").email("email@email.com").telefone("2198511-3423").build(),
                Contato.builder().nome("Rock Bauer").email("email@email.com").telefone("2198511-3333").build()
        ).collect(Collectors.toList());
    }

    private Contato getContato() {
        return Contato.builder().nome("Jack tequila").email("email@email.com").telefone("2198511-0076").build();

    }
    private Endereco getEndereco() {
        return Endereco.builder().logradouro("Praia de Boatafogo").cidade("Rio de Janeiro").estado("RJ").pais("Brasil").numero(316).complemento("Ap 219").build();
    }

    private Conjugue getConjugue() {
        Endereco endereco = getEndereco();
        Conjugue cjg = Conjugue.builder().
                endereco(endereco).
                nomeConjugue("Pequena Raimunda").
                rg("020.133.110-5").
                cpf("105.932.327-39").build();
        return cjg;
    }


}