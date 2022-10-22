package br.com.precatorio.contrato;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.domain.EnumEstadoCivil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class ContratoService {

    private final String TEMPLATE_SOLTEIRO = "c:\\test\\TEMPLATE_SOLTEIRO.docx";
    private final String TEMPLATE_CASADO = "c:\\test\\TEMPLATE_CASADO.docx";
    private String TEMPLATE_SAIDA = "c:\\test\\";

    public String gerarContratoDocx(Cliente cliente) throws IOException{
        String input = TEMPLATE_SOLTEIRO;
        String output = TEMPLATE_SAIDA + cliente.getCpf() + ".docx";

        if (cliente.getEstadoCivil().equals(EnumEstadoCivil.CASADO))
            input = TEMPLATE_CASADO;

        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(input)))
        ) {
            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            //Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    if(Objects.isNull(docText))
                        continue;

                    //replacement
                    docText = updateNome(cliente, docText);
                    docText = updateRG(cliente, docText);
                    docText = updateEstadoCivil(cliente, docText);

                    xwpfRun.setText(docText, 0);
                }
            }

            // save the docs
            try (FileOutputStream out = new FileOutputStream(output)) {
                doc.write(out);
            }

        }

        return output;
    }

    //ENTE_DEVEDOR
    //COD_BANCO
    //NOME_BANCO

    private static String updateEnteDevedor(Cliente cliente, String docText) {
        return docText.replace("${ENTE_DEVEDOR}", cliente.getEstadoCivil());
    }

    private static String updateCodBanco(Cliente cliente, String docText) {
        return docText.replace("${COD_BANCO}", cliente.getEstadoCivil());
    }

    private static String updateNomeBanco(Cliente cliente, String docText) {
        return docText.replace("${NOME_BANCO}", cliente.getEstadoCivil());
    }

    private static String updateEstadoCivil(Cliente cliente, String docText) {
        return docText.replace("${ESTADO_CIVIL}", cliente.getEstadoCivil());
    }

    private static String updateDataAtual(Cliente cliente, String docText) {
        //TODO Data atual formatada
        return docText.replace("${DATA_ATUAL}", cliente.getEstadoCivil());
    }


    private static String updateNacionalidade(Cliente cliente, String docText) {
        return docText.replace("${NACIONALIDADE}", cliente.getNacionalidade());
    }

    //Dados Contrato
    private static String valorContrato(Cliente cliente, String docText) {
        return docText.replace("${VALOR_CONTRATO}", cliente.getContratos().stream().findFirst().get().getValorContrato().toString());
    }

    private static String valorContratoPorExtenso(Cliente cliente, String docText) {
        //TODO impl logica
        return docText.replace("${VALOR_CONTRATO_EXTENSO}", cliente.getContratos().stream().findFirst().get().getValorContrato().toString());
    }

    private static String valorNegociadoPorExtenso(Cliente cliente, String docText) {
        //TODO impl logica
        return docText.replace("${VALOR_NEGOCIADO_POR_EXTENSO}", cliente.getContratos().stream().findFirst().get().getValorAcordado().toString());
    }

    private static String updateNumProcesso(Cliente cliente, String docText) {
        return docText.replace("${NUM_PROCESSO}", cliente.getContratos().stream().findFirst().get().getNumProcesso());
    }

    private static String updateOrigemTramitacao(Cliente cliente, String docText) {
        return docText.replace("${ORIGEM_TRAMITACAO}", cliente.getContratos().stream().findFirst().get().getOrigemTramitacao());
    }

    private static String updateNumPrecatorio(Cliente cliente, String docText) {
        return docText.replace("${NUM_PRECATORIO}", cliente.getContratos().stream().findFirst().get().getNumPrecatorio());
    }

    private static String valorNegociado(Cliente cliente, String docText) {
        return docText.replace("${VALOR_NEGOCIADO}", cliente.getContratos().stream().findFirst().get().getValorAcordado().toString());
    }

    private static String updateRG(Cliente cliente, String docText) {
        return docText.replace("${RG}", cliente.getRg());
    }

    private static String updateCPF(Cliente cliente, String docText) {
        return docText.replace("${CPF}", cliente.getCpf());
    }

    private static String updateCidade(Cliente cliente, String docText) {
        return docText.replace("${CIDADE}", cliente.getEndereco().getCidade());
    }

    private static String updateNome(Cliente cliente, String docText) {
        return docText.replace("${NOME}", cliente.getContato().getNome().toUpperCase());
    }

    private static String updateLogradouro(Cliente cliente, String docText) {
        return docText.replace("${LOGRADOURO}", cliente.getEndereco().getLogradouro());
    }

    private static String updateLogradouroConjugue(Cliente cliente, String docText) {
        return docText.replace("${LOGRADOURO_CONJUGUE}", cliente.getConjugue().getEndereco().getLogradouro());
    }

    private static String updateCidadeConjugue(Cliente cliente, String docText) {
        return docText.replace("${CIDADE_CONJUGUE}", cliente.getConjugue().getEndereco().getCidade());
    }

    private static String updateNomeConjugue(Cliente cliente, String docText) {
        return docText.replace("${NOME_CONJUGUE}", cliente.getConjugue().getNomeConjugue());
    }

    private static String updateEnderecoConjugue(Cliente cliente, String docText) {
        //TODO impl endereço no padrão
        return docText.replace("${ENDERECO_CONJUGUE}", cliente.getContato().getNome().toUpperCase());
    }

    private static String updateEndereco(Cliente cliente, String docText) {
        //TODO impl endereço no padrão
        return docText.replace("${ENDERECO}", cliente.getContato().getNome().toUpperCase());
    }

}
