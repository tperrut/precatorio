package br.com.precatorio.system;

import br.com.precatorio.cliente.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class SystemController {

    @GetMapping("/")
    public String getAllClientes() {

        return "Xamaaaaaaaaaaaa!!!!! ";
    }

}
