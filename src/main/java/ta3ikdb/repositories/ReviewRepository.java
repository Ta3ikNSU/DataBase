package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta3ikdb.entitys.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
