package cl.crisgvera.tallerjpa.controlador;

import cl.crisgvera.tallerjpa.modelo.OficinaUber;
import cl.crisgvera.tallerjpa.rest.collection.OficinaUberCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;

@Controller
@RequestMapping("/oficinas-uber")
public class OficinaUberControlador {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public String index(Model model) {
        Collection<OficinaUber> oficinasUber = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/rest/oficina-uber")
                .retrieve()
                .bodyToMono(OficinaUberCollection.class)
                .block()
                .getOficinasUber();

        model.addAttribute("oficinasUber", oficinasUber);
        return "forward:/";
    }

}
