package cl.crisgvera.tallerjpa.modelo;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pasajero {

    @Id
    @GeneratedValue
    @Column(name = "PASAJERO_ID")
    private Long id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 35)
    private String email;

    @Column(length = 15)
    private String celular;

    @OneToMany(mappedBy = "pasajero",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Viaje> viajes = new HashSet<>();

    public void addViaje(Viaje viaje) {
        viajes.add(viaje);
    }

    public void deleteViaje(Viaje viaje) {
        viajes.remove(viaje);
    }

}
