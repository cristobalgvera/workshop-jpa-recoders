package cl.crisgvera.tallerjpa.modelo;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OFICINA_UBER")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OficinaUber {

    @Id
    @GeneratedValue
    @Column(name = "OFICINA_UBER_ID")
    private Long id;

    @Column(length = 30)
    private String pais;

    @Column(length = 40)
    private String ciudad;

    @Column(length = 50)
    private String ubicacion;

    @OneToMany(mappedBy = "oficinaUber", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Chofer> choferes = new HashSet<>();

    public void addChofer(Chofer chofer) {
        choferes.add(chofer);
        chofer.setOficinaUber(this);
    }

    public void deleteChofer(Chofer chofer) {
        choferes.remove(chofer);
        chofer.setOficinaUber(null);
    }

}
