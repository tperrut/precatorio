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

    private static String updateEstadoCivil(Cliente cliente, String docText) {
        return docText.replace("${estadoCivil}", cliente.getEstadoCivil());
    }

    private static String updateNacionalidade(Cliente cliente, String docText) {
        return docText.replace("${nacionalidade}", cliente.getNacionalidade());
    }

    private static String valorContratoPorExtenso(Cliente cliente, String docText) {
        return docText.replace("${valorContrato}", cliente.getEstadoCivil());
    }

    private static String valorOferecidoPorExtenso(Cliente cliente, String docText) {
        return docText.replace("${valorOferecido}", cliente.getEstadoCivil());
    }

    private static String updateRG(Cliente cliente, String docText) {
        return docText.replace("${RG}", cliente.getRg());
    }

    private static String updateCPF(Cliente cliente, String docText) {
        return docText.replace("${CPF}", cliente.getCpf());
    }

    private static String updateNome(Cliente cliente, String docText) {
        return docText.replace("${nome}", cliente.getContato().getNome().toUpperCase());
    }


}
