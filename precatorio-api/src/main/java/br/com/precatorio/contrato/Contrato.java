package br.com.precatorio.contrato;

import br.com.precatorio.cliente.Cliente;
import br.com.precatorio.domain.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CONTRATO")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contrato extends AbstractEntity {

    @Column
    private Double valorContrato;
    @Column
    private Double valorAcordado;

//    @NotNull(message = "Número do Processo obrigatório.")
    @Column
    private String numProcesso;

    //    @NotNull(message = "Origem Tramitacao obrigatório.")
    @Column
    private String origemTramitacao;

    //    @NotNull(message = "Número Precatorio obrigatório.")
    @Column
    private String numPrecatorio;

    @Column
    private String enteDevedor;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
