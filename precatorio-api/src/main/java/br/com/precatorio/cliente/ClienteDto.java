package br.com.precatorio.cliente;

import br.com.precatorio.cliente.conjugue.Conjugue;
import br.com.precatorio.contato.Contato;
import br.com.precatorio.contrato.Contrato;
import br.com.precatorio.domain.EnumEstadoCivil;
import br.com.precatorio.endereco.Endereco;
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

    @NotNull(message = "CPF obrigatório.")
    private String estadoCivil;

    @NotNull(message = "CPF obrigatório.")
    private String nacionalidade;

    @NotNull(message = "Telefone obrigatório.")
    private String telefone;
    @NotNull(message = "Logradouro obrigatório.")
    private String logradouro;
    @NotNull(message = "Cidade obrigatório.")
    private String cidade;
    @NotNull(message = "Estado obrigatório.")
    @Size(min = 2, max = 2, message = "RG obrigatório.")
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





    @NotNull(message = "Número do Processo obrigatório.")
    private String numProcesso;

    @NotNull(message = "Origem Tramitacao obrigatório.")
    private String origemTramitacao;

    @NotNull(message = "Número Precatorio obrigatório.")
    private String numPrecatorio;

    // data conjgue


    @NotNull(message = "Nome obrigatório.")
    private String nomeConjugue;
    @NotNull(message = "Profissão obrigatório.")
    private String profissaoConjugue;

    // adress conjugue
    @NotNull(message = "Logradouro obrigatório.")
    private String logradouroConjugue;
    @NotNull(message = "Cidade obrigatório.")
    private String cidadeConjugue;
    @NotNull(message = "Estado obrigatório.")
    @Size(min = 2, max = 2, message = "RG obrigatório.")
    private String estadoConjugue;
    @NotNull(message = "País obrigatório.")
    private String paisConjugue;
    @NotNull(message = "Número obrigatório.")
    private Integer numeroEnderecoConjugue;

    //@NotNull(message = "Complemento obrigatório.")
    private String complementoConjugue;
    @NotNull(message = "Cep obrigatório.")
    private String cepConjugue;

    public static ClienteDto convertClienteToDto(Cliente cliente) {
        //TODO rever a lógica do valor "findAny"
        Double valorContrato1 = cliente.getContratos().isEmpty() ? ZERO : cliente.getContratos().stream().findAny().get().getValorContrato();
        return ClienteDto.builder()
                .cpf(cliente.getCpf())
                .rg(cliente.getRg())
                .estadoCivil(cliente.getEstadoCivil())
                .nacionalidade(cliente.getNacionalidade())
                .estadoCivil(cliente.getEstadoCivil())
                .cep(cliente.getEndereco().getCep())
                .cidade(cliente.getEndereco().getCidade())
                .complemento(cliente.getEndereco().getComplemento())
                .pais(cliente.getEndereco().getPais())
                .estado(cliente.getEndereco().getEstado())
                .logradouro(cliente.getEndereco().getLogradouro())
                .numero(cliente.getEndereco().getNumero())
                .nomeContato(cliente.getContato().getNome())
                .email(cliente.getContato().getEmail())
                .telefone(cliente.getContato().getTelefone())
                .percentual(cliente.getPercentual())
                .valorContrato(valorContrato1)
                .build();
    }
    public static Cliente convertDtoToCliente(ClienteDto dto){

        Contrato contrato = getContrato(dto);

        Contato contato = getContato(dto);

        Endereco endereco = getEnderecoByDto(dto);

        if(dto.getEstadoCivil().equals(EnumEstadoCivil.CASADO.valor())) {
            Conjugue conjugue = getConjugue(dto);
            Endereco enderecoConjugue = getEnderecoConjugueByDto(dto);
        }

        Cliente cliente = Cliente.builder()
            .rg(dto.getRg())
            .cpf(dto.getCpf())
            .estadoCivil(dto.getEstadoCivil())
            .nacionalidade(dto.getNacionalidade())
            .contato(contato)
            .endereco(endereco)
            .updateAt(LocalDateTime.now())
            .percentual(dto.getPercentual())
            .build();

        cliente.addContrato(contrato);
        contato.setCliente(cliente);

        return cliente;

    }

    private static Endereco getEnderecoConjugueByDto(ClienteDto dto) {
        Endereco endereco = Endereco.builder()
                .complemento(dto.getComplementoConjugue())
                .cidade(dto.getCidadeConjugue())
                .logradouro(dto.getLogradouroConjugue())
                .cep(dto.getCepConjugue())
                .numero(dto.getNumeroEnderecoConjugue())
                .estado(dto.getEstadoConjugue())
                .pais(dto.getPaisConjugue())
                .build();
        return endereco;
    }

    private static Conjugue getConjugue(ClienteDto dto) {
        Conjugue conjugue = Conjugue.builder()
                .rg(dto.getRg())
                .nomeConjugue(dto.getNomeConjugue())
                .cpf(dto.getCep())
                .profissao(dto.getProfissao())
                .nacionalidade(dto.getNacionalidade())
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
