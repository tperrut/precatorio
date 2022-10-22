package br.com.precatorio.cliente;

import br.com.precatorio.cliente.conjugue.Conjugue;
import br.com.precatorio.contato.Contato;
import br.com.precatorio.contrato.Contrato;
import br.com.precatorio.domain.EnumEstadoCivil;
import br.com.precatorio.endereco.Endereco;
import br.com.precatorio.system.exception.ConverterException;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    public static final double TAXA_PADRAO = 80.0;
    public static final double ZERO = 0.0;

    private Long id;

    @NotNull(message = "Nome is required.")
    @Size(min = 10, message = "Nome is required.")
    private String nomeContato;

    @NotNull(message = "RG obrigatório.")
    @Size(min = 10, message = "RG obrigatório.")
    private String rg;

    @NotNull(message = "CPF obrigatório.")
    @Size(min = 10, message = "CPF obrigatório.")
    private String cpf;

    @NotNull(message = "Email obrigatório.")
    @Size(min = 10, message = "Email obrigatório.")
    @Email(message = "Formato de Email inválido .")
    private String email;

    @NotNull(message = "Estado Cívil obrigatório.")
    private String estadoCivil;

    @NotNull(message = "Ente Devedor obrigatório.")
    private String enteDevedor;

    @NotNull(message = "Nome do Banco obrigatório.")
    private String NomeBanco;

    @NotNull(message = "Código obrigatório.")
    private String CodBanco;

    //TODO mapear ENTE_DEVEDOR
    //COD_BANCO
    //NOME_BANCO
    //DATA_ATUAL

    @NotNull(message = "Telefone obrigatório.")
    private String telefone;
    @NotNull(message = "Logradouro obrigatório.")
    private String logradouro;
    @NotNull(message = "Cidade obrigatório.")
    private String cidade;
    @NotNull(message = "Estado obrigatório.")
//    @Size(min = 2, max = 2, message = "RG obrigatório.")
    private String estado;
    @NotNull(message = "País obrigatório.")
    private String pais;
    @NotNull(message = "Número obrigatório.")
    private Integer numero;

    //@NotNull(message = "Complemento obrigatório.")
    private String complemento;
    @NotNull(message = "Cep obrigatório.")
    private String cep;

    @NotNull(message = "Valor obrigatório.")
    private Double valorContrato;

    @NotNull(message = "Percentual obrigatório.")
    private Double percentual;
    @NotNull(message = "Profissão obrigatório.")
    private String profissao;

    @NotNull(message = "Nacionalidade obrigatório.")
    private String nacionalidade;


    @NotNull(message = "Número do Processo obrigatório.")
    private String numProcesso;

    @NotNull(message = "Origem Tramitacao obrigatório.")
    private String origemTramitacao;

    @NotNull(message = "Número Precatorio obrigatório.")
    private String numPrecatorio;

    // data conjgue

    private String nomeConjugue;
    private String profissaoConjugue;
    private String nacionalidadeConjugue;
    private String rgConjugue;
    private String cpfConjugue;

    // adress conjugue

    private String logradouroConjugue;
    private String cidadeConjugue;
    private String estadoConjugue;
    private String paisConjugue;
    private Integer numeroConjugue;
    private String complementoConjugue;
    private String cepConjugue;
    private Double valorAcordado;


    public static ClienteDto convertClienteToDto(Cliente cliente) throws ConverterException {
        Double valorContrato;
        Contrato contrato = null;
        if (cliente.getContratos().size() == 0) {
            valorContrato = ZERO;
        } else {
            //TODO rever a lógica do valor "findAny"
            contrato = cliente.getContratos().stream().findAny().get();
            valorContrato = contrato.getValorContrato();
        }

        ClienteDto dto = ClienteDto.builder()
                .cep(cliente.getEndereco().getCep())

                .cidade(cliente.getEndereco().getCidade())

                .complemento(cliente.getEndereco().getComplemento())

                .cpf(cliente.getCpf())

                .email(cliente.getContato().getEmail())
                .estadoCivil(cliente.getEstadoCivil())

                .estado(cliente.getEndereco().getEstado())

                .logradouro(cliente.getEndereco().getLogradouro())

                .nacionalidade(cliente.getNacionalidade())

                .nomeContato(cliente.getContato().getNome())

                .numero(cliente.getEndereco().getNumero())

                .pais(cliente.getEndereco().getPais())

                .percentual(cliente.getPercentual())

                .profissao(cliente.getProfissao())

                .rg(cliente.getRg())

                .telefone(cliente.getContato().getTelefone())
                .build();

        boolean isMarried = cliente.getEstadoCivil().equals(EnumEstadoCivil.CASADO.getValor());
        boolean hasContrato = cliente.getContratos().size() > 0;

        if (isMarried) popularConjugue(dto, cliente);
        if (hasContrato) populateContrato(dto, cliente);

        return dto;
    }

    private static void populateContrato(ClienteDto dto, Cliente cliente) {
        Contrato contrato = cliente.getContratos().stream().findFirst().get();
        dto.setValorContrato(contrato.getValorContrato());
        dto.setNumPrecatorio(contrato.getNumPrecatorio());
        dto.setNumProcesso(contrato.getNumProcesso());
        dto.setOrigemTramitacao(contrato.getOrigemTramitacao());
        dto.setValorAcordado(contrato.getValorAcordado());

    }

    private static void popularConjugue(ClienteDto dto, Cliente cliente) {
        dto.setCepConjugue(cliente.getConjugue().getEndereco().getCep());
        dto.setCidadeConjugue(cliente.getConjugue().getEndereco().getCidade());
        dto.setComplementoConjugue(cliente.getConjugue().getEndereco().getComplemento());
        dto.setCpfConjugue(cliente.getConjugue().getCpf());
        dto.setEstadoConjugue(cliente.getConjugue().getEndereco().getEstado());
        dto.setLogradouroConjugue(cliente.getConjugue().getEndereco().getLogradouro());
        dto.setNacionalidadeConjugue(cliente.getConjugue().getNacionalidade());
        dto.setNumeroConjugue(cliente.getConjugue().getEndereco().getNumero());
        dto.setNomeConjugue(cliente.getConjugue().getNomeConjugue());
        dto.setRgConjugue(cliente.getConjugue().getRg());
        dto.setProfissaoConjugue(cliente.getConjugue().getProfissao());
        dto.setPaisConjugue(cliente.getEndereco().getPais());


    }

    public static Cliente convertDtoToCliente(ClienteDto dto) throws ConverterException {

        Contrato contrato = getContrato(dto);

        Contato contato = getContato(dto);

        Endereco endereco = getEnderecoByDto(dto);
        Conjugue conjugue = null;
        Endereco enderecoConjugue = null;
        if (dto.getEstadoCivil().equals(EnumEstadoCivil.CASADO.getValor())) {
            conjugue = getConjugue(dto);
            enderecoConjugue = getEnderecoConjugueByDto(dto);
            conjugue.setEndereco(enderecoConjugue);
        }

        Cliente cliente = Cliente.builder()
                .rg(dto.getRg())
                .cpf(dto.getCpf())
                .estadoCivil(dto.getEstadoCivil())
                .nacionalidade(dto.getNacionalidade())
                .contato(contato)
                .profissao(dto.getProfissao())
                .endereco(endereco)
                .updateAt(LocalDateTime.now())
                .percentual(dto.getPercentual())
                .build();

        cliente.addContrato(contrato);
        contato.setCliente(cliente);
        cliente.setConjugue(conjugue);
        conjugue.setCliente(cliente);
        return cliente;

    }

    private static Endereco getEnderecoConjugueByDto(ClienteDto dto) {
        Endereco endereco = Endereco.builder()
                .complemento(dto.getComplementoConjugue())
                .cidade(dto.getCidadeConjugue())
                .logradouro(dto.getLogradouroConjugue())
                .cep(dto.getCepConjugue())
                .numero(dto.getNumeroConjugue())
                .estado(dto.getEstadoConjugue())
                .pais(dto.getPaisConjugue())
                .build();
        return endereco;
    }

    private static Conjugue getConjugue(ClienteDto dto) {
        Conjugue conjugue = Conjugue.builder()
                .rg(dto.getRgConjugue())
                .nomeConjugue(dto.getNomeConjugue())
                .cpf(dto.getCepConjugue())
                .profissao(dto.getProfissaoConjugue())
                .nacionalidade(dto.getNacionalidadeConjugue())
                .updateAt(LocalDateTime.now())
                .build();
        return conjugue;

    }

    private static Contrato getContrato(ClienteDto dto) {
        Contrato contrato = Contrato.builder()
                .valorContrato(dto.getValorContrato())
                .valorAcordado(dto.getValorContrato() * (dto.getPercentual()/100))
                .numPrecatorio(dto.getNumPrecatorio())
                .origemTramitacao(dto.getOrigemTramitacao())
                .numProcesso(dto.getNumProcesso())
                .build();
        return contrato;
    }

    private static Contato getContato(ClienteDto dto) {
        Contato contato = Contato.builder()
                .nome(dto.getNomeContato())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .build();
        return contato;
    }

    private static Contato getContatoByCliente(Cliente dto) {
        Contato contato = Contato.builder()
                .nome(dto.getContato().getNome())
                .email(dto.getContato().getEmail())
                .telefone(dto.getContato().getTelefone())
                .build();
        return contato;
    }


    private static Endereco getEnderecoByDto(ClienteDto dto) {
        Endereco endereco = Endereco.builder()
                .complemento(dto.getComplemento())
                .cidade(dto.getCidade())
                .logradouro(dto.getLogradouro())
                .cep(dto.getCep())
                .numero(dto.getNumero())
                .estado(dto.getEstado())
                .pais(dto.getPais())
                .build();
        return endereco;
    }

    private static Endereco getEnderecoByCliente(Cliente cliente) {
        Endereco endereco = Endereco.builder()
                .complemento(cliente.getEndereco().getComplemento())
                .cidade(cliente.getEndereco().getCidade())
                .logradouro(cliente.getEndereco().getLogradouro())
                .cep(cliente.getEndereco().getCep())
                .numero(cliente.getEndereco().getNumero())
                .estado(cliente.getEndereco().getEstado())
                .pais(cliente.getEndereco().getPais())
                .build();
        return endereco;
    }
}
