package cl.crisgvera.tallerjpa.modelo;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Auto {

    @Id
    @GeneratedValue
    @Column(name = "AUTO_ID")
    private Long id;

    @Column(length = 30)
    private String marca, modelo;
    private int anio;

    @OneToOne(mappedBy = "auto")
    private Chofer chofer;

}
