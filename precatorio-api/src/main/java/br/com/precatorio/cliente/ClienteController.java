package br.com.precatorio.cliente;

import br.com.precatorio.endereco.EnderecoRepository;
import br.com.precatorio.system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    EnderecoRepository enderecoRepository;



  

    // get all Clientes
    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> all = repository.findAll();

        return ResponseEntity.ok(all);
    }

    // create Cliente rest api
    @PostMapping("/cliente")
    public Cliente createCliente(@RequestBody ClienteDto dto) {
        Cliente entity = ClienteDto.convertDtoToCliente(dto);
        entity.setEndereco(enderecoRepository.save(entity.getEndereco()));
        return repository.save(entity);
    }

    // get Cliente by id rest api
    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        return ResponseEntity.ok(ClienteDto.convertClienteToDto(cliente));
    }


    @PutMapping("/cliente/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id,@Valid @RequestBody ClienteDto dto){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        Cliente updated = ClienteDto.convertDtoToCliente(dto);
        updated.setId(cliente.getId());
        updated = repository.save(updated);

        return ResponseEntity.ok(ClienteDto.convertClienteToDto(updated));
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
