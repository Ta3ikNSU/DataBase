package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ta3ikdb.entitys.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
