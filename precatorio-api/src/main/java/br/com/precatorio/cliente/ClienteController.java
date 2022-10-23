package br.com.precatorio.cliente;

import br.com.precatorio.system.erro.CustomError;
import br.com.precatorio.system.exception.ConverterException;
import br.com.precatorio.system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ClienteController {
    @Autowired
    ClienteServiceImpl service;
    @Autowired
    ClienteRepository repository;


    // get all Clientes
    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        List<Cliente> all = repository.findAll();

        return ResponseEntity.ok(all);
    }

    // create Cliente rest api
    @PostMapping("/cliente")
    public ResponseEntity createCliente(@Valid @RequestBody ClienteDto dto) {
        Cliente cliente = null;
        try {
            service.salvar(dto);

            System.out.println(cliente);
        } catch (Exception e) {
            return handleRunTime(new RuntimeException("Erro ao salvar o Cliente, contate o Thiagão do Gongolo!!!"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return ResponseEntity.ok().build();
    }

    // get Cliente by id rest api
    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        ClienteDto body;
        try {
            Cliente cliente = service.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));


            body = ClienteDto.convertClienteToDto(cliente);
        } catch (ConverterException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao Converter os dados do Cliente, contate o Thiagão do Gongolo!!!", e);

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o Cliente: " + id + ", contate o Thiagão do Gongolo!!!", e);
        }

        return ResponseEntity.ok(body);
    }


    @PutMapping("/cliente/{id}")
    public ResponseEntity updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteDto dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        try {
            service.update(dto, cliente);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao alterar o Cliente", e);
        }

        return ResponseEntity.ok().build();
    }

    // delete Cliente rest api
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCliente(@PathVariable Long id) {
        Cliente Cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + id));

        repository.delete(Cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    protected ResponseEntity<Object> handleRunTime(RuntimeException ex, HttpStatus status) {
        return new ResponseEntity<>(new CustomError(ex.getMessage()), status);
    }


}
