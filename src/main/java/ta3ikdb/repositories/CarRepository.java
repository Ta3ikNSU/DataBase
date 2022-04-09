package ta3ikdb.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ta3ikdb.entitys.AnnouncementState;
import ta3ikdb.entitys.Car;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor  {
    Optional<Car> findByVinNumberAndAnnouncementStatus(Long vinNumber, AnnouncementState state);
}
