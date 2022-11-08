package br.com.precatorio.endereco;

import br.com.precatorio.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "ENDERECO")
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Endereco extends AbstractEntity {

    private String logradouro;
    private String cidade;
    private String estado;
    private String pais;
    private Integer numero;
    private String complemento;
    private String cep;

    private String bairro;


    @Override
    public String toString() {
        return "Endereco{" +
                ", logradouro='" + logradouro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
