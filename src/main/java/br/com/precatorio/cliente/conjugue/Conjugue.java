package br.com.precatorio.cliente.conjugue;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.domain.AbstractEntity;
import br.com.precatorio.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONJUGUE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Conjugue extends AbstractEntity {


    //    @NotEmpty(message = "Rg não pode ser vazio")
    @Column(nullable = true)
    private String rg;

    //    @NotEmpty(message = "CPF não pode ser vazio")
    @Column(nullable = true)
    private String cpf;


    //    @NotEmpty(message = "Estado Civil não pode ser vazio")
//    @Column(nullable = true)
//    private String estadoCivil;

    //    @NotEmpty(message = "Nacionalidade não pode ser vazio")
    @Column()
    private String nacionalidade;


    //    @NotEmpty(message = "Nome não pode ser vazio")
    @Column(nullable = true)
    private String nomeConjugue;

    //    @NotEmpty(message = "Nome não pode ser vazio")
    @Column(nullable = true)
    private String profissao;


    @OneToOne
    private Endereco endereco;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
//    @Column(name= "updated_at", nullable = false, columnDefinition = "DATE")
    private LocalDateTime updateAt;


//    @Override
//    public String toString() {
//        return "Cliente{" +
//                "Nome='" + this.getNomeContato() + '\'' +
//
//                "rg='" + rg + '\'' +
//                ", cpf='" + cpf + '\'' +
//                ", estadoCivil='" + estadoCivil + '\'' +
//                ", nacionalidade='" + nacionalidade + '\'' +
//                ", endereco=" + endereco +
//                ", contato=" + contato +
//                ", contratos=" + contratos +
//                ", updateAt=" + updateAt +
//                '}';
//    }
}
