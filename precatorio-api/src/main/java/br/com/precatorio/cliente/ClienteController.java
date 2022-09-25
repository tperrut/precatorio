package br.com.precatorio.cliente;

import br.com.precatorio.contato.Contato;
import br.com.precatorio.contrato.Contrato;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ClienteController {
    @Autowired
    ClienteService service;
    @Autowired
    ClienteRepository repository;

    @GetMapping(path = "cliente/download/{id}")

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


  

    // get all Clientes
    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> all = repository.findAll();

        return ResponseEntity.ok(all);
    }

    // create Cliente rest api
    @PostMapping("/cliente")
    public Cliente createCliente(@RequestBody ClienteDto dto) {
        return repository.save(dto.convertDtoToCliente(dto));
    }

    // get Cliente by id rest api
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente Cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));
        return ResponseEntity.ok(Cliente);
    }

    // update Cliente rest api

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteDto dto){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        Contato newCtt = Contato.builder()
                .nome(dto.getNomeContato())
                .email(dto.getEmail())
                    .build();
        cliente.setContato(newCtt);

        Contrato contrato = Contrato.builder()
                .valorContrato(dto.getValorContrato())
                .valorContrato(dto.getValorContrato() * (dto.getPercentual()/100))
                .build();
        cliente.setContratos(List.of(contrato));

        Endereco newContato = Endereco.builder()
                .logradouro(dto.getLogradouro())
                .cidade(dto.getCidade())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .pais(dto.getPais())
                .estado(dto.getEstado())
                .build();
        cliente.setEndereco(newContato);


        Cliente updatedCliente = repository.save(cliente);

        return ResponseEntity.ok(updatedCliente);
    }

    // delete Cliente rest api
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCliente(@PathVariable Long id){
        Cliente Cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        repository.delete(Cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
