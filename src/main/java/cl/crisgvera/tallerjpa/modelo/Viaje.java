package cl.crisgvera.tallerjpa.modelo;

import cl.crisgvera.tallerjpa.modelo.relacional.ViajeId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Viaje implements Serializable {

    @EmbeddedId
    ViajeId id; // Debe ser un atributo público por razones relativas a JPA y su método de adquisición de datos

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId(value = "CHOFER_ID") // Nombre establecido en 'ViajeId' para 'choferId'
    @JoinColumn(name = "CHOFER_ID")
    @JsonIgnore
    Chofer chofer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId(value = "PASAJERO_ID") // Nombre establecido en 'ViajeId' para 'pasajeroId'
    @JoinColumn(name = "PASAJERO_ID")
    @JsonIgnore
    Pasajero pasajero;

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
        id = new ViajeId(chofer.getId(), pasajero.getId());
        this.chofer = chofer;
        this.pasajero = pasajero;
    }

}
