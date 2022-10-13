package br.com.precatorio.system.configuration;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.cliente.ClienteRepository;
import br.com.precatorio.cliente.conjugue.Conjugue;
import br.com.precatorio.cliente.conjugue.ConjugueRepository;
import br.com.precatorio.contato.Contato;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class PopulateDBConfiguration {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ConjugueRepository conjugueRepository;

    @Bean
    public void populateDB(){

            Contato jack_tequila = Contato.builder().nome("Jack tequila").email("email@email.com").telefone("2198511-0076").build();
            Contato jack_estripador = Contato.builder().nome("Jack Estripador").email("email@email.com").telefone("2198511-000").build();
            Contato jack_jhonson = Contato.builder().nome("Jhon Jhonson").email("email@email.com").telefone("2198511-8687").build();
            Contato jay_tequila = Contato.builder().nome("Jay tequila").email("email@email.com").telefone("2198511-3423").build();
            Contato rock_bauer = Contato.builder().nome("Rock Bauer").email("email@email.com").telefone("2198511-3333").build();


            Endereco endereco  = Endereco.builder().logradouro("Praia de Boatafogo").cidade("Rio de Janeiro").estado("RJ").pais("Brasil").numero(316).complemento("Ap 219").build();
            Endereco endereco1 = Endereco.builder().logradouro("Rua A").cidade("Betim").estado("MG").pais("Brasil").numero(32).complemento("Ap 234").build();
            Endereco endereco2 = Endereco.builder().logradouro("Rua C").cidade("Val Paraiso").estado("GO").pais("Brasil").numero(34).complemento("Ap 444").build();
            Endereco endereco3 = Endereco.builder().logradouro("Rua D").cidade("Val Paraiso").estado("GO").pais("Brasil").numero(34).complemento("Ap 222").build();
            Endereco endereco4 = Endereco.builder().logradouro("Rua B").cidade("Val Paraiso").estado("GO").pais("Brasil").numero(34).complemento("Ap 111").build();



            Stream.of(
                    //Endereco.builder().logradouro("Rua V").cidade("Val Paraiso").estado("GO").pais("Brasil").numero(34).complemento("Ap 444").build()
                    endereco,endereco1,endereco2,endereco3,endereco4
            ).forEach(
                    adress -> enderecoRepository.saveAndFlush(adress)
            );

            Conjugue cjg = Conjugue.builder().
                    endereco(endereco4).
                    nomeConjugue("Pequena Raimunda").
                    rg("020.133.110-5").
                    cpf("105.932.327-39").
                    estadoCivil("Casado")
                    .build();
            cjg = conjugueRepository.saveAndFlush(cjg);

            Cliente cliente1 = Cliente.builder().rg("020.133.110-5").cpf("105.932.327-39").estadoCivil("Casado")
                    .endereco(endereco)
                    .conjugue(cjg)
                    .contato(jack_tequila).build();
            Cliente cliente2 = Cliente.builder().rg("042.141.155-2").cpf("102.932.876-32").estadoCivil("Solteiro")
                    .endereco(endereco1)
                    .contato(jack_estripador).build();
            Cliente cliente3 = Cliente.builder().rg("770.122.199-7").cpf("574.563.909-11").estadoCivil("Casado")
                    .endereco(endereco3)
                    .contato(jay_tequila).build();
            Cliente cliente4 = Cliente.builder().rg("029.111.179-6").cpf("132.213.000-22").estadoCivil("Solteiro")
                    .endereco(endereco2)
                    .contato(jack_jhonson).build();

            Cliente cliente5 = Cliente.builder().rg("330.144.178-8").cpf("755.777.978-55").estadoCivil("Casado")
                    .endereco(endereco4)
                    .contato(rock_bauer).build();

            /*
            endereco.setCliente(cliente1);
            endereco1.setCliente(cliente2);
            endereco2.setCliente(cliente3);
            endereco3.setCliente(cliente4);
            endereco4.setCliente(cliente5);
             */
            jack_estripador.setCliente(cliente1);
            jack_tequila.setCliente(cliente2);
            jack_jhonson.setCliente(cliente3);
            jay_tequila.setCliente(cliente4);
            rock_bauer.setCliente(cliente5);

            List list = Stream.of(cliente1,cliente2,cliente4,cliente3,cliente4,cliente5).collect(Collectors.toList());
            clienteRepository.saveAllAndFlush(list);




//          clienteRepository.findAll().forEach(System.out::println);
        };

}
