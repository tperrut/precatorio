package br.com.precatorio.cliente;

import br.com.precatorio.contato.Contato;
import br.com.precatorio.contrato.Contrato;
import br.com.precatorio.endereco.Endereco;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
public class    ClienteDto {

    public static final double TAXA_PADRAO = 80.0;
    public static final double ZERO = 0.0;
    private Long id;
    private String nomeContato;
    private String rg;
    private String cpf;
    private String email;
    private String estadoCivil;
    private String nacionalidade;
    private String telefone;
    private String logradouro;
    private String cidade;
    private String estado;
    private String pais;
    private Integer numero;
    private String complemento;
    private String cep;
    private Cliente cliente;
    private Double valorContrato;
    private Double percentual;

    public static ClienteDto convertClienteToDto(Cliente cliente) {
        //TODO rever a lógica do valor
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
        endereco.setCliente(cliente);

        return cliente;

    }

    private static Contrato getContrato(ClienteDto dto) {
        Contrato contrato = Contrato.builder()
                .valorContrato(dto.getValorContrato())
                .valorAcordado(dto.getValorContrato() * (dto.getPercentual()/100))
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
