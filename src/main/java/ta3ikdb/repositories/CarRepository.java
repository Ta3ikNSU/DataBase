package ta3ikdb.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ta3ikdb.entities.AnnouncementState;
import ta3ikdb.entities.Car;

import java.util.Collection;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car>, PagingAndSortingRepository<Car, Long> {
    Optional<Car> findByVinNumberAndAnnouncementStatus(Long vinNumber, AnnouncementState state);

    Optional<Car> getCarByVinNumber(Long vin);

    @Query("select c from Car c where c.id in :ids  and c.announcement.status = :state")
    Page<Car> findByIdInAndState(@Param("ids") Collection<Long> ids, @Param("state") AnnouncementState state, Pageable pageable);

    @Query("select c from Car c where c.id in :ids ")
    Page<Car> findByIdIn(@Param("ids") Collection<Long> ids, Pageable pageable);

    Car save(Car entity);
}
