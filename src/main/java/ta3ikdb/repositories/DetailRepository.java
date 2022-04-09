package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ta3ikdb.entitys.Detail;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long>, JpaSpecificationExecutor {

}
