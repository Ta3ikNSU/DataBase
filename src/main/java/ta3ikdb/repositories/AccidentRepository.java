package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ta3ikdb.entities.Accident;

import java.util.List;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {

    // Критерий (Запрос с подзапросом)
    @Query("select a from Accident a where (select c from Car c where c.vinNumber = :vin) member a.cars")
    List<Accident> accidentsByVinNumber(@Param("vin") Long vin);
}
