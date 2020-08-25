package cl.crisgvera.tallerjpa;

import cl.crisgvera.tallerjpa.modelo.*;
import cl.crisgvera.tallerjpa.servicio.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class TallerJpaApplicationTests {

    @Autowired
    private PasajeroServicio pasajeroServicio;

    @Autowired
    private ViajeServicio viajeServicio;

    @Autowired
    private ChoferServicio choferServicio;

    @Autowired
    private OficinaUberServicio oficinaUberServicio;

    @Autowired
    private AutoServicio autoServicio;

    @Transactional
    @Test
    void contextLoads() {
        Pasajero pasajero = pasajeroServicio.findById(1L);
        Collection<Viaje> viaje = viajeServicio.findByPasajeroId(pasajero.getId());

        viaje.forEach(v -> {
            log.info(v);
            assertSame(v.getId().getPasajeroId(), pasajero.getId());
        });
    }

    @Test
//    @Transactional
    public void crearChoferAsociarAutoYOficinaUber() {
        // Creamos objetos
        Chofer chofer = new Chofer();
        chofer.setNombre("CHOFER_NOMBRE");
        chofer.setRating(9);

        Auto auto = new Auto();
        auto.setMarca("AUTO_MARCA");
        auto.setModelo("AUTO_MODELO");
        auto.setAnio(2020);

        OficinaUber oficinaUber = new OficinaUber();
        oficinaUber.setPais("OFICINA_PAIS");
        oficinaUber.setCiudad("OFICINA_CIUDAD");
        oficinaUber.setUbicacion("OFICINA_UBICACIÓN");

        // Asociamos objetos
        chofer.setAuto(auto);
        auto.setChofer(chofer);
        oficinaUber.addChofer(chofer);

        // Almacenamos en la base de datos
        oficinaUber = oficinaUberServicio.save(oficinaUber);
        chofer = choferServicio.findById(oficinaUber.getChoferes()
                .stream()
                .findFirst()
                .get()
                .getId());
        auto = autoServicio.findById(chofer.getAuto().getId());

        log.info("Chofer desde el auto -> " + auto.getChofer());

        oficinaUber.getChoferes().forEach(c -> log.info("Chofer desde la oficina de Uber -> " + c));

        assertEquals(chofer.getAuto()
                        .getId(),
                oficinaUber.getChoferes()
                        .stream()
                        .findFirst()
                        .get()
                        .getAuto()
                        .getId());
    }

}
