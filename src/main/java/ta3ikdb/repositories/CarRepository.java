package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta3ikdb.entitys.Car;

@Repository
public interface CarRepository extends JpaRepository <Car, Long> {

}
