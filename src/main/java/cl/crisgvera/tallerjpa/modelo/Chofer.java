package cl.crisgvera.tallerjpa.modelo;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private Auto auto;

    @ManyToOne
    @JoinColumn(name = "OFICINA_UBER_ID", nullable = false)
    private OficinaUber oficinaUber;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "VIAJE",
            joinColumns = @JoinColumn(name = "CHOFER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PASAJERO_ID"))
    private Set<Pasajero> pasajeros = new HashSet<>();

    public void addPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
    }

    public void deletePasajero(Pasajero pasajero) {
        pasajeros.remove(pasajero);
    }

}
