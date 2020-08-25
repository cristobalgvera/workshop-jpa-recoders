package cl.crisgvera.tallerjpa.repositorio;

import cl.crisgvera.tallerjpa.modelo.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ChoferRepositorio extends JpaRepository<Chofer, Long> {
    /*
        SELECT *
        FROM CHOFER C
                INNER JOIN AUTO A ON A.AUTO_ID = C.AUTO_ID
        WHERE ANIO = ?;
     */
    Collection<Chofer> findAllByAuto_Anio(int anioAuto);

    /*
        SELECT *
        FROM CHOFER C
                INNER JOIN AUTO A2 on A2.AUTO_ID = C.AUTO_ID
        WHERE MODELO = ?
          AND RATING = ?
        ORDER BY NOMBRE;
     */
    Collection<Chofer> findAllByAuto_ModeloAndRatingOrderByNombre(String modeloAuto, int rating);

    /*
        SELECT *
        FROM (SELECT *
              FROM CHOFER C
                       INNER JOIN AUTO A2 on A2.AUTO_ID = C.AUTO_ID
              WHERE MARCA = ?)
        WHERE ROWNUM = 1;
     */
    Optional<Chofer> findFirstByAuto_Marca(String marca);

}
