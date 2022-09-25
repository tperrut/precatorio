package br.com.precatorio.cliente;

import br.com.precatorio.contato.Contato;
import br.com.precatorio.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
public class ClienteDto {

    @JsonIgnore
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

    public Cliente convertDtoToCliente(ClienteDto dto){


        Contato contato = Contato.builder()
                .nome(dto.getNomeContato())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .build();

        Endereco endereco = Endereco.builder()
                .complemento(dto.getComplemento())
                .cidade(dto.getCidade())
                .logradouro(dto.getLogradouro())
                .cep(dto.getCep())
                .numero(dto.getNumero())
                .estado(dto.getEstado())
                .pais(dto.getPais())
                .build();

        Cliente cliente = Cliente.builder()
                .rg(dto.getRg())
                .cpf(dto.getCpf())
                .estadoCivil(dto.getEstadoCivil())
                .nacionalidade(dto.getNacionalidade())
                .contato(contato)
                .endereco(endereco)
                .build();
        contato.setCliente(cliente);
        endereco.setCliente(cliente);
        return cliente;

    }
}
