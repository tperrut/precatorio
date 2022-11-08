package br.com.precatorio.contrato;

import br.com.precatorio.cliente.ClienteRepository;
import br.com.precatorio.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/contrato/")
public class ContratoController {
    @Autowired
    ContratoService service;
    @Autowired
    ClienteRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping(path = "cliente/download/{id}")
    public HttpEntity<byte[]> gerarContratoWordV1(@PathVariable Long id) {
        var cliente = repository.findById(id);

        if (!cliente.isPresent())
            return (HttpEntity<byte[]>) HttpEntity.EMPTY;

        try {

            String output = service.gerarContratoDocx(cliente.get());

            byte[] arquivo = Files.readAllBytes(Paths.get(output));

            HttpHeaders httpHeaders = new HttpHeaders();
            String nomeArquivo = cliente.get().getCpf();
            httpHeaders.add("Content-Disposition", "attachment;filename=\"" + nomeArquivo + ".docx\"");
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

            return entity;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    @GetMapping(path = "/download/{id}")
    public void gerarContratoWord(@PathVariable Long id, HttpServletResponse response) {
        var cliente = repository.findById(id);

//        if(!cliente.isPresent())  return ResponseEntity.notFound().build();

        try {

            String output = service.gerarContratoDocx(cliente.get());

            byte[] arquivo = Files.readAllBytes(Paths.get(output));
            File file = Paths.get(output).toFile();
            String nomeArquivo = cliente.get().getCpf();

            response.setContentLength((int) file.length());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + nomeArquivo + ".docx\"");
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
