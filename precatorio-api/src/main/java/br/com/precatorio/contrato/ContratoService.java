package br.com.precatorio.contrato;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.domain.EnumEstadoCivil;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.system.util.NumeroPorExtenso;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ContratoService {

    HashMap<Integer, String> meses = new HashMap<>();

    private final String TEMPLATE_SOLTEIRO = "c:\\test\\TEMPLATE_SOLTEIRO.docx";
    private final String TEMPLATE_CASADO = "c:\\test\\TEMPLATE_CASADO.docx";
    private String TEMPLATE_SAIDA = "c:\\test\\";

    public ContratoService() {
        this.meses = new HashMap<>();
        meses.put(1, "Janeiro");
        meses.put(2, "Fevereiro");
        meses.put(3, "Março");
        meses.put(4, "Abril");
        meses.put(5, "Maio");
        meses.put(6, "Junho");
        meses.put(7, "Julho");
        meses.put(8, "Agosto");
        meses.put(9, "Setembro");
        meses.put(10, "Outubro");
        meses.put(11, "Novembro");
        meses.put(12, "Dezembro");
    }

    public String gerarContratoDocx(Cliente cliente) throws IOException {
        String input = TEMPLATE_SOLTEIRO;
        String output = TEMPLATE_SAIDA + cliente.getCpf() + ".docx";

        if (cliente.getEstadoCivil().equals(EnumEstadoCivil.CASADO.getValor()))
            input = TEMPLATE_CASADO;

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

                    if (docText.contains("DATA_ATUAL") || docText.contains("VALOR_CTT") || docText.contains("BANCO") || docText.contains("PROMITENTE VENDEDOR:"))
                        System.out.println();


                    //Dados do Cliente
                    docText = updateNome(cliente, docText);
                    docText = updateNacionalidade(cliente, docText);
                    docText = updateProfissao(cliente, docText);
                    docText = updateEstadoCivil(cliente, docText);
                    docText = updateRG(cliente, docText);
                    docText = updateCPF(cliente, docText);
                    docText = updateEnteDevedor(cliente, docText);


                    //Dados bancarios
                    docText = updateNomeBanco(cliente, docText);
                    docText = updateCodBanco(cliente, docText);
                    docText = updateAgencia(cliente, docText);
                    docText = updateContaCorrente(cliente, docText);

                    //Endereco Cliente


                    //Dados do Conjugue


                    //Dados do Contrato
                    docText = updateDataAtual(cliente, docText);
                    docText = updateNumProcesso(cliente, docText);
                    docText = updateNumPrecatorio(cliente, docText);
                    docText = updateOrigemTramitacao(cliente, docText);
                    docText = valorContrato(cliente, docText);

                    docText = valorNegociado(cliente, docText);

                    //Dados do Customizados
                    docText = valorNegociadoPorExtenso(cliente, docText);
                    docText = valorContratoPorExtenso(cliente, docText);
                    docText = updateEndereco(cliente, docText);

                    //TODO Validar com Rafael   docText = updateEnderecoConjugue(cliente, docText);


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

    private String updateAgencia(Cliente cliente, String docText) {
        return docText.replace("{AG}", cliente.getAgencia());
    }

    private String updateContaCorrente(Cliente cliente, String docText) {
        return docText.replace("{NUM_CC}", cliente.getContaCorrente());
    }


    private String updateProfissao(Cliente cliente, String docText) {
        return docText.replace("{PROFISSÃO}", cliente.getProfissao());

    }

    private String updateProfissaoConjugue(Cliente cliente, String docText) {
        return docText.replace("{PROFISSÃO_CONJUGUE}", cliente.getContratos().stream().findFirst().get().getEnteDevedor());

    }

    private String updateEnteDevedor(Cliente cliente, String docText) {
        return docText.replace("{ENTE_DEVEDOR}", cliente.getContratos().stream().findFirst().get().getEnteDevedor());
    }

    private String updateCodBanco(Cliente cliente, String docText) {
        return docText.replace("{COD_BANCO}", cliente.getCodBanco());
    }

    private String updateNomeBanco(Cliente cliente, String docText) {
        return docText.replace("{BANCO}", cliente.getNomeBanco());
    }

    private String updateEstadoCivil(Cliente cliente, String docText) {
        return docText.replace("{ESTADO_CIVIL}", cliente.getEstadoCivil());
    }

    private String updateDataAtual(Cliente cliente, String docText) {
        //TODO Data atual formatada
        return docText.replace("DATA_ATUAL", createDataFormatada());
    }

    private String createDataFormatada() {
        //Rio de Janeiro, 16 de Julho de 2021.
        String mes = meses.get(LocalDate.now().getMonthValue());
        Integer year = LocalDate.now().getYear();
        return MessageFormat.format("Rio de Janeiro, {0} de {1} de {2}.", LocalDate.now().getDayOfMonth(), mes, year.toString());
    }


    private String updateNacionalidade(Cliente cliente, String docText) {
        return docText.replace("{NACIONALIDADE}", cliente.getNacionalidade());
    }

    //Dados Contrato
    private String valorContrato(Cliente cliente, String docText) {
        return docText.replace("VALOR_CONTRATO", cliente.getContratos().stream().findFirst().get().getValorContrato().toString());
    }

    private String valorContratoPorExtenso(Cliente cliente, String docText) {
        //TODO impl logica
        NumeroPorExtenso numeroPorExtenso = new NumeroPorExtenso(cliente.getContratos().stream().findFirst().get().getValorContrato());
        numeroPorExtenso.show();

        return docText.replace("{VALOR_CTT_EXTENSO}", numeroPorExtenso.toString());
    }

    private String valorNegociadoPorExtenso(Cliente cliente, String docText) {
        //TODO impl logica
        NumeroPorExtenso numeroPorExtenso = new NumeroPorExtenso(cliente.getContratos().stream().findFirst().get().getValorAcordado());
        numeroPorExtenso.show();

        return docText.replace("{VALOR_NEGOCIADO_POR_EXTENSO}", numeroPorExtenso.toString());
    }

    private String updateNumProcesso(Cliente cliente, String docText) {
        return docText.replace("{NUM_PROCESSO}", cliente.getContratos().stream().findFirst().get().getNumProcesso());
    }

    private String updateOrigemTramitacao(Cliente cliente, String docText) {
        return docText.replace("{ORIGEM_TRAMITACAO}", cliente.getContratos().stream().findFirst().get().getOrigemTramitacao());
    }

    private String updateNumPrecatorio(Cliente cliente, String docText) {
        return docText.replace("{NUM_PRECATORIO}", cliente.getContratos().stream().findFirst().get().getNumPrecatorio());
    }

    private String valorNegociado(Cliente cliente, String docText) {
        return docText.replace("{VALOR_NEGOCIADO}", cliente.getContratos().stream().findFirst().get().getValorAcordado().toString());
    }

    private String updateRG(Cliente cliente, String docText) {
        return docText.replace("{RG}", cliente.getRg());
    }

    private String updateCPF(Cliente cliente, String docText) {
        return docText.replace("{CPF}", cliente.getCpf());
    }

    private String updateCidade(Cliente cliente, String docText) {
        return docText.replace("{CIDADE}", cliente.getEndereco().getCidade());
    }

    private String updateNome(Cliente cliente, String docText) {
        return docText.replace("NOME", cliente.getContato().getNome().toUpperCase());
    }

    private String updateLogradouro(Cliente cliente, String docText) {
        return docText.replace("{LOGRADOURO}", cliente.getEndereco().getLogradouro());
    }

    private String updateLogradouroConjugue(Cliente cliente, String docText) {
        return docText.replace("{LOGRADOURO_CONJUGUE}", cliente.getConjugue().getEndereco().getLogradouro());
    }

    private String updateCidadeConjugue(Cliente cliente, String docText) {
        return docText.replace("{CIDADE_CONJUGUE}", cliente.getConjugue().getEndereco().getCidade());
    }

    private String updateNomeConjugue(Cliente cliente, String docText) {
        return docText.replace("{NOME_CONJUGUE}", cliente.getConjugue().getNomeConjugue());
    }

    private String updateEnderecoConjugue(Cliente cliente, String docText) {
        //TODO impl endereço no padrão
        // "na cidade de São Paulo,
        // à Rua Francisco Martins,
        // 115,
        // bairro Jd. Bela Vista,
        // CEP: 13515-000"
        return docText.replace("{ENDERECO_CONJUGUE}", montarEndereco(cliente.getConjugue().getEndereco()));
    }

    private CharSequence montarEndereco(Endereco endereco) {
        String complemento = Objects.isNull(endereco.getComplemento()) ? "" : endereco.getComplemento();
        String logradouro = endereco.getLogradouro();
        String numero = endereco.getNumero().toString();
        if (!complemento.equals("")) {
            numero = ", " + complemento;
        }
        return MessageFormat.format("na cidade de {0}, à {1}, {2}, bairro {3}. CEP: {4} ",
                endereco.getCidade(), logradouro, endereco.getNumero(), endereco.getBairro(), endereco.getCep());
    }

    private String updateEndereco(Cliente cliente, String docText) {
        //TODO impl endereço no padrão
        return docText.replace("{ENDERECO}", montarEndereco(cliente.getEndereco()));
    }

}
