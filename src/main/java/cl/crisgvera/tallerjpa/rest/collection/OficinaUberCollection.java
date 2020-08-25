package cl.crisgvera.tallerjpa.rest.collection;

import cl.crisgvera.tallerjpa.modelo.OficinaUber;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OficinaUberCollection {

    private Collection<OficinaUber> oficinasUber;

}
