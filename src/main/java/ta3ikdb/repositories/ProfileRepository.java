package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta3ikdb.entitys.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
