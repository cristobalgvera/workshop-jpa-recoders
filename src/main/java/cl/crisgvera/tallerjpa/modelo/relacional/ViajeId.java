package cl.crisgvera.tallerjpa.modelo.relacional;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ViajeId implements Serializable {

    @Column(name = "CHOFER_ID") // TIP: Ayuda a no equivocarse en el mapeo posterior
    private Long choferId;

    @Column(name = "PASAJERO_ID")
    private Long pasajeroId;

}
