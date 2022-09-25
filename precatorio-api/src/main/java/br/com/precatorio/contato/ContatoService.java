package br.com.precatorio.contato;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    public String exportReport(List<Contato> contatoes) {

        return "report generated in path : " ;
    }
}
