package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ta3ikdb.entitys.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByAuthorId(Long authorId);

    @Query("select r from Review r where r.car.vinNumber = ?1")
    Review findByCar(Integer vinNumber);
}
