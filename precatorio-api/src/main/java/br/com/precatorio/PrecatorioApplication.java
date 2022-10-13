package br.com.precatorio;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.contato.Contato;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.cliente.ClienteRepository;
import br.com.precatorio.cliente.conjugue.Conjugue;
import br.com.precatorio.cliente.conjugue.ConjugueRepository;
import br.com.precatorio.endereco.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class PrecatorioApplication {
    private static final Logger log = LoggerFactory.getLogger(PrecatorioApplication.class);

    public static void main(String[] args) {
           log.info("Vamo q vamo com Thiagão do GONGOLO!!!!!!!");
          SpringApplication.run(PrecatorioApplication.class, args);
    }

}
