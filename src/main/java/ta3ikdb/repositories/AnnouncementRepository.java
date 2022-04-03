package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ta3ikdb.entitys.Announcement;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByPriceBetween(Long price, Long price2);
}
