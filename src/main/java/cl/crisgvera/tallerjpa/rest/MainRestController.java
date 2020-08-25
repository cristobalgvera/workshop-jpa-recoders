package cl.crisgvera.tallerjpa.rest;

import cl.crisgvera.tallerjpa.rest.collection.OficinaUberCollection;
import cl.crisgvera.tallerjpa.servicio.OficinaUberServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MainRestController {

    @Autowired
    private OficinaUberServicio oficinaUberServicio;

    @GetMapping("/oficina-uber")
    public OficinaUberCollection getAll() {
        return new OficinaUberCollection(oficinaUberServicio.findAll());
    }

}
