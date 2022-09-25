import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.system.util.NumeroPorExtenso;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class PocConvertWorToPdf {
    public static void run() throws IOException {
        String docPath = "c:\\test\\TEMPLATE_SOLTEIRO_FINAL.docx";
        String pdfPath = "c:\\test\\TEMPLATE_SOLTEIRO_FINAL.pdf";

        InputStream in = new FileInputStream(docPath);
        XWPFDocument document = new XWPFDocument(in);
        PdfOptions options = PdfOptions.create();
        OutputStream out = new FileOutputStream(pdfPath);
        PdfConverter.getInstance().convert(document, out, options);

        closeRecursos(document, out);

    }

    private static void closeRecursos(XWPFDocument document, OutputStream out) throws IOException {
        document.close();
        out.close();
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
                    if(Objects.isNull(docText))
                        continue;

                    System.out.println("<--- Antes---> "+ docText);
                    //replacement
                    docText = docText.replace("${nome}", cliente.getContato().getNome());

                    docText = docText.replace("${estado_civil}", cliente.getEstadoCivil());
                    docText = docText.replace("${extenso}", new NumeroPorExtenso(25000).toString());

                    System.out.println("<--- Depois---> "+ docText);
                    xwpfRun.setText(docText, 0);
                }
            }

            // save the docs
            try (FileOutputStream out = new FileOutputStream(output)) {
                doc.write(out);

                closeRecursos(doc, out);
            }



        }

    }


}
