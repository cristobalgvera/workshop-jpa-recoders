package cl.crisgvera.tallerjpa.modelo;

import cl.crisgvera.tallerjpa.modelo.relacional.ViajeId;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Viaje {

    @EmbeddedId
    ViajeId id; // Debe ser un atributo público por razones relativas a JPA y su método de adquisición de datos

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId(value = "CHOFER_ID") // Nombre establecido en 'ViajeId' para 'choferId'
    @JoinColumn(name = "CHOFER_ID")
    private Chofer chofer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId(value = "PASAJERO_ID") // Nombre establecido en 'ViajeId' para 'pasajeroId'
    @JoinColumn(name = "PASAJERO_ID")
    private Pasajero pasajero;

    @Column(length = 50)
    private String ubicacionSalida, ubicacionLlegada;
    private LocalDateTime horaSalida, horaLlegada;

    /*
        Estos métodos harán referencia a que el viaje definirá cómo actuarán tanto el
        Chofer como el Pasajero. El cómo implementar esto dependerá de la situación.
        Para este caso lo estableceremos en esta entidad, sin embargo, podemos
        perfectamente implementarlo desde alguna de las entidades relacionadas, o bien,
        desde ambas por separado.
     */

    public void createViaje(Chofer chofer, Pasajero pasajero) {
        this.chofer = chofer;
        chofer.addViaje(this);
        this.pasajero = pasajero;
        pasajero.addViaje(this);
    }

    public void deleteViaje(Chofer chofer, Pasajero pasajero) {
        this.chofer = null;
        chofer.deleteViaje(this);
        this.pasajero = null;
        pasajero.deleteViaje(this);
    }

}
