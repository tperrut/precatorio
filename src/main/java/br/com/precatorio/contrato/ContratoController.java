package br.com.precatorio.contrato;

import br.com.precatorio.cliente.ClienteRepository;
import br.com.precatorio.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "https://precatorio-app.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/v1/contrato/")
public class ContratoController {
    @Autowired
    ContratoService service;
    @Autowired
    ClienteRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

  /*  @GetMapping(path = "cliente/download/{id}")
    public void gerarContratoWordV1(@PathVariable Long id, HttpServletResponse response) {
        var cliente = repository.findById(id);

        try {

            byte[] arquivo = service.gerarContratoDocx(cliente.get());

            String nomeArquivo = cliente.get().getCpf();
            response.setHeader("Content-Disposition", "attachment;filename=\"" + nomeArquivo + ".docx\"");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

            response.setContentLength(arquivo.length);

            FileCopyUtils.copy(arquivo, response.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    @GetMapping(path = "cliente/download/{id}")
    public ResponseEntity<Resource> gerarContratoWordV1(@PathVariable Long id) {


        var cliente = repository.findById(id);

        try {
            byte[] arquivo = service.gerarContratoDocx(cliente.get());

            ByteArrayResource resource = new ByteArrayResource(arquivo);

            HttpHeaders httpHeaders = new HttpHeaders();
            String nomeArquivo = cliente.get().getCpf();
            String headerValue = "attachment; filename=\"" + nomeArquivo + ".docx\"";
            httpHeaders.add("Content-Disposition", headerValue);
            httpHeaders.add("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .contentLength(arquivo.length)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                    .body(resource);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
