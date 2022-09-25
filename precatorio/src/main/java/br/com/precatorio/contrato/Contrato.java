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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column
    private Double valorContrato;
    @Column
    private Double valorAcordado;
}
