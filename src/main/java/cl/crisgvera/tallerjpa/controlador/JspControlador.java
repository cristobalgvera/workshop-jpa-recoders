package cl.crisgvera.tallerjpa.controlador;

import cl.crisgvera.tallerjpa.modelo.Chofer;
import cl.crisgvera.tallerjpa.modelo.Pasajero;
import cl.crisgvera.tallerjpa.modelo.Viaje;
import cl.crisgvera.tallerjpa.modelo.relacional.ViajeId;
import cl.crisgvera.tallerjpa.servicio.ChoferServicio;
import cl.crisgvera.tallerjpa.servicio.PasajeroServicio;
import cl.crisgvera.tallerjpa.servicio.ViajeServicio;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@Controller
@RequestMapping("/jsp")
public class JspControlador {

    @Autowired
    private ChoferServicio choferServicio;

    @Autowired
    private PasajeroServicio pasajeroServicio;

    @Autowired
    private ViajeServicio viajeServicio;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("viaje", new Viaje());
        return "index";
    }

    @Transactional
    @PostMapping("/solicitar-uber")
    public String solicitarUber(@ModelAttribute("viaje") Viaje viaje, Model model) {
        Pasajero pasajero = nuevoPasajero();
        pasajero = pasajeroServicio.save(pasajero); // Debemos utilizar el identificador del pasajero
        log.info("Pasajero ID: " + pasajero.getId() + " - Creado");

        Chofer chofer = choferServicio.findAll()
                .stream()
                .findAny()
                .orElse(null);
        log.info("Petición de choferes a la base de datos existosa");

        viaje.setHoraSalida(LocalDateTime.now()); // Damos un valor a la fecha
        viaje.createViaje(chofer, pasajero); // Creamos la llave primario-foránea
        viaje = viajeServicio.save(viaje); // Guardamos el viaje en nuestra base de datos
        log.info("Viaje ID: " + viaje.getId() + " - Creado");
        model.addAttribute("viajeSolicitado", viaje);
        return "index";
    }

    @Transactional
    @PostMapping("/terminar-viaje")
    public String terminarViaje(@ModelAttribute("viajeSolicitado") Viaje viaje, Model model) {
        viaje = viajeServicio.findById(viaje.getId());
        log.info("Viaje ID: " + viaje.getId() + " - Encontrado");

        if (viaje.getHoraLlegada() == null) {
            viaje.setHoraLlegada(LocalDateTime.now());
            log.info("Hora de término del viaje ID " + viaje.getId() + " actualizada");
        }

        model.addAttribute("viajeSolicitado", viaje);
        model.addAttribute("viaje", new Viaje());
        return "index";
    }

    private Pasajero nuevoPasajero() {
        // Pasajero genérico
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre("Daniela Fernández");
        pasajero.setEmail("daniela.fernandez@gmail.com");
        pasajero.setCelular("+56994846532");
        return pasajero;
    }
}
