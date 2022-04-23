package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ta3ikdb.entitys.AnnouncementState;
import ta3ikdb.entitys.Car;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor {
    Optional<Car> findByVinNumberAndAnnouncementStatus(Long vinNumber, AnnouncementState state);

    Optional<Car> getCarById(Long id);
}
