package br.com.precatorio.contato;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CONTATO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Contato extends AbstractEntity {


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //    @NotEmpty(message = "Nome não pode ser vazio")
    @Column(nullable = true)
    private String nome;


    //    @NotEmpty(message = "Telefone não pode ser vazio")
    @Column()
    private String telefone;

    //    @NotEmpty(message = "Tipo Processo não pode ser vazio")
    @Column()
    private String tipoProcesso;


    //    @NotEmpty(message = "Tipo Precatorio não pode ser vazio")
    @Column()
    private String tipoPrecatorio;

    //    @NotEmpty(message = "Email não pode ser vazio")
    @Column()
    private String email;


    @Override
    public String toString() {
        return "Contato{" +
                "cliente=" + cliente +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", tipoProcesso='" + tipoProcesso + '\'' +
                ", tipoPrecatorio='" + tipoPrecatorio + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
