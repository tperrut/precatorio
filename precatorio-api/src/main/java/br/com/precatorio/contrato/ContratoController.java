package br.com.precatorio.contrato;

import br.com.precatorio.cliente.ClienteRepository;
import br.com.precatorio.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ContratoController {
    @Autowired
    ContratoService service;
    @Autowired
    ClienteRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping(path = "download/{id}")
    public HttpEntity<byte[]> gerarContratoWord(@PathVariable Long id) {
        var cliente =  repository.findById(id);

        if(!cliente.isPresent())
            return (HttpEntity<byte[]>) HttpEntity.EMPTY;

        try {

            String output = service.gerarContratoDocx(cliente.get());

            byte[] arquivo = Files.readAllBytes( Paths.get(output) );

            HttpHeaders httpHeaders = new HttpHeaders();
            String nomeArquivo = cliente.get().getCpf();
            httpHeaders.add("Content-Disposition", "attachment;filename=\""+ nomeArquivo + ".docx\"");

            HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

            return entity;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


  


}
