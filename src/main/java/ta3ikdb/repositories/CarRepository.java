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

    Optional<Car> getCarByVinNumber(Long vin);

    @Query("select c from Car c where c.id in :ids and c.announcement.price between :left and :right and c.announcement.status = :state")
    List<Car> findByIdInAndAnnouncement_PriceBetweenAndState(@Param("ids") Collection<Long> ids,
                                                     @Param("left") Long priceStart,
                                                     @Param("right") Long priceEnd,
                                                     @Param("state") AnnouncementState state);

    @Query("select c from Car c where c.id in :ids and c.announcement.price between :left and :right")
    List<Car> findByIdInAndAnnouncement_PriceBetween(@Param("ids") Collection<Long> ids,
                                                             @Param("left") Long priceStart,
                                                             @Param("right") Long priceEnd);



}
