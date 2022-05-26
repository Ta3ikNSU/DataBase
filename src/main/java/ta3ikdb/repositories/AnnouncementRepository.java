package ta3ikdb.repositories;

import liquibase.pro.packaged.Q;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ta3ikdb.entities.Announcement;

import java.sql.Timestamp;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByPriceBetween(Long price, Long price2);
}
