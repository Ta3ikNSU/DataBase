package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ta3ikdb.entities.AnnouncementState;
import ta3ikdb.entities.Car;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor {
    Optional<Car> findByVinNumberAndAnnouncementStatus(Long vinNumber, AnnouncementState state);

    Optional<Car> getCarById(Long id);
    List<Car> findByIdInAndAnnouncement_PriceBetween(Collection<Long> ids, Long priceStart, Long priceEnd);

}
