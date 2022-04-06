package ta3ikdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ta3ikdb.entitys.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository <Car, Long> {
    List<Car> findCarsByBrand(String brand);
    List<Car> findCarsByBrandAndModel(String brand, String model);
    List<Car> findCarsByEngineCapacityBetween(Integer engineCapacity, Integer engineCapacity2);
    List<Car> findCarsByEnginePowerBetween(Integer enginePower, Integer enginePower2);
    List<Car> findByColor(Integer color);
    List<Car> findByMileageBetween(String mileage, String mileage2);

    @Query("select c from Car c where c.announcement.price > ?1 and c.announcement.price < ?2")
    List<Car> findByCostBetween(Integer left, Integer right);
}