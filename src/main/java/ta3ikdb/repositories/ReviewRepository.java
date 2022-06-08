package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ta3ikdb.entities.Car;
import ta3ikdb.entities.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByAuthorId(Long authorId);

    List<Review> findReviewsByCar(Car car);

    @Query("select r from Review r where r.car.brand=:brand and r.car.model=:model")
    List<Review> findReviewsByCarBrandAndModel(@Param("brand") String brand, @Param("model") String model);

    @Query(value = "select c.* " +
            "from car c inner join announcement a on a.id = c.announcement_id " +
            "inner join review r on c.id = r.car_id " +
            "where a.status = 0", nativeQuery = true)
    List<Car> getCarsWithOpenAnnouncement();

}
