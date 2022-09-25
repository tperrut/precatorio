package br.com.precatorio.cliente;

import br.com.precatorio.contato.Contato;
import br.com.precatorio.contrato.Contrato;
import br.com.precatorio.domain.AbstractEntity;
import br.com.precatorio.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "CLIENTE")
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Cliente extends AbstractEntity {


    //    @NotEmpty(message = "Rg não pode ser vazio")
        @Column(nullable = true)
        private String rg;

        //    @NotEmpty(message = "CPF não pode ser vazio")
        @Column(nullable = true)
        private String cpf;



    //    @NotEmpty(message = "Estado Civil não pode ser vazio")
            @Column(nullable = true)
        private String estadoCivil;

    //    @NotEmpty(message = "Nacionalidade não pode ser vazio")
        @Column()
        private String nacionalidade;

        @OneToOne(
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
        )
        private Endereco endereco;

        @OneToOne(
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
        )
        private Contato contato;

        @OneToMany
        private List<Contrato> contratos;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern="yyyy-MM-dd")
    //    @Column(name= "updated_at", nullable = false, columnDefinition = "DATE")
        private LocalDateTime updateAt;

        public String getNomeContato(){
        return contato.getNome();
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "rg='" + this.getNomeContato() + '\'' +

                "rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", endereco=" + endereco +
                ", contato=" + contato +
                ", contratos=" + contratos +
                ", updateAt=" + updateAt +
                '}';
    }
}
