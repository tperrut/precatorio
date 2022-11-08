import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.contato.Contato;
import br.com.precatorio.domain.EnumEstadoCivil;
import br.com.precatorio.system.util.NumeroPorExtenso;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class PocPoiUpdateDocx {
    public static void main(String[] args) throws IOException {
        PocPoiUpdateDocx obj = new PocPoiUpdateDocx();

        Contato ctt = Contato.builder()
                .nome("Thiperrut")
                .build();
        Cliente jack = new Cliente();
        jack.setEstadoCivil(EnumEstadoCivil.CASADO.getValor());
        jack.setContato(ctt);

        obj.updateDocument("c:\\test\\TEMPLATE_SOLTEIRO.docx", "c:\\test\\TEMPLATE_SOLTEIRO_FINAL.docx", jack);

        PocConvertWorToPdf.run();
    }

    private static String updateNome(Cliente cliente, String docText) {
        docText = docText.replace("{NOME}", cliente.getContato().getNome().toUpperCase());
        return docText;
    }

    void updateDocument(String input, String output, Cliente cliente)
            throws IOException {

        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(input)))
        ) {
            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            //Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    if (Objects.isNull(docText))
                        continue;


                    //replacement
                    docText = updateNome(cliente, docText);

                    docText = docText.replace("{ESTADO_CIVIL}", cliente.getEstadoCivil());
                    docText = docText.replace("{VALOR_CONTRATO_EXTENSO}", new NumeroPorExtenso(25000).toString());
                    xwpfRun.setText(docText, 0);
                }
            }

            // save the docs
            try (FileOutputStream out = new FileOutputStream(output)) {
                doc.write(out);
            }

        }

    }


}
