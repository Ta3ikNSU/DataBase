package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ta3ikdb.entitys.Detail;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findDetailsByBrand(String brand);
    List<Detail> findDetailsByType(Integer type);
    List<Detail> findDetailsByBrandAndModel(String brand, String model);
}
