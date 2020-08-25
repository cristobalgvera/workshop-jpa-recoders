package cl.crisgvera.tallerjpa.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Chofer {

    @Id
    @GeneratedValue
    @Column(name = "CHOFER_ID") // Definimos un nombre específico para la columna
    private Long id; // Nombramos el atributo como Long por mayor capacidad de memoria

    @Column(length = 50) // Definimos un tamaño específico para la columna
    private String nombre;
    private int rating;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "AUTO_ID")
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Auto auto;

    @ManyToOne
    @JoinColumn(name = "OFICINA_UBER_ID", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private OficinaUber oficinaUber;

    @OneToMany(mappedBy = "chofer",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Viaje> viajes = new HashSet<>();

}
