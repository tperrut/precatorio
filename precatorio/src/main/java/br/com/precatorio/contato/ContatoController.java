package br.com.precatorio.contato;

import br.com.precatorio.system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ContatoController {
    @Autowired
    ContatoService service;
    @Autowired
    ContatoRepository repository;

    @GetMapping(path = "/clientes/report")
    public String gerarReport() {
        var clientes =  repository.findAll();


            return service.exportReport(clientes);

    }

  

    // get all Clientes
    @GetMapping("/clientes")
    public List<Contato> getAllClientes(){
        return repository.findAll();
    }

    // create Cliente rest api
    @PostMapping("/clientes")
    public Contato createCliente(@RequestBody Contato Contato) {
        return repository.save(Contato);
    }

    // get Cliente by id rest api
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Contato> getClienteById(@PathVariable Long id) {
        Contato Contato = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));
        return ResponseEntity.ok(Contato);
    }

    // update Cliente rest api

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Contato> updateCliente(@PathVariable Long id, @RequestBody Contato contatoDetails){
        Contato Contato = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        Contato.setNome(contatoDetails.getNome());
        Contato.setTelefone (contatoDetails.getTelefone());
        Contato.setEmail(contatoDetails.getEmail());

        Contato updatedContato = repository.save(Contato);
        return ResponseEntity.ok(updatedContato);
    }

    // delete Cliente rest api
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCliente(@PathVariable Long id){
        Contato Contato = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        repository.delete(Contato);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
