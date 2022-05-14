package ta3ikdb.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ta3ikdb.DTO.CarAnnouncementsRequestDTO;
import ta3ikdb.DTO.CarDTO;
import ta3ikdb.entities.AnnouncementState;
import ta3ikdb.entities.Car;
import ta3ikdb.entities.Detail;
import ta3ikdb.mapper.DTOMapper;
import ta3ikdb.repositories.CarRepository;
import ta3ikdb.repositories.DetailRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementFinderService {

    private final Logger log = LogManager.getLogger();
    @Autowired
    CarRepository carRepository;

    @Autowired
    DetailRepository detailRepository;

    private <T> Predicate createPredicateByList(List<T> values, String name, Root<Car> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (values != null && !values.isEmpty()) {
            predicates = values.stream().map((value) -> criteriaBuilder.equal(root.get(name), value)).toList();
        }
        return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
    }

    private Predicate createPredicateBetween(Integer left, Integer right, String name, Root<Car> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (left != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get(name), left));
        }
        if (right != null) {
            predicates.add(criteriaBuilder.lessThan(root.get(name), right));
        }
        return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
    }

    public List<Car> findCarsByParameters(List<String> brands,
                                          List<String> models,
                                          List<Integer> transmissions,
                                          List<Integer> gears,
                                          Integer minEngineCapacity,
                                          Integer maxEngineCapacity,
                                          Integer minEnginePower,
                                          Integer maxEnginePower,
                                          List<Integer> colors,
                                          List<String> mileages,
                                          List<Integer> performances,
                                          Long minPrice,
                                          Long maxPrice,
                                          AnnouncementState state) {

        Specification<Car> querySpec = (root, query, criteriaBuilder) -> {
            if (brands != null && !brands.isEmpty()) {
                criteriaBuilder.and(createPredicateByList(brands, "brand", root, criteriaBuilder));
            }

            if (models != null && !models.isEmpty()) {
                criteriaBuilder.and(createPredicateByList(models, "model", root, criteriaBuilder));
            }

            if (transmissions != null && !transmissions.isEmpty()) {
                criteriaBuilder.and(createPredicateByList(transmissions, "transmission", root, criteriaBuilder));
            }

            if (gears != null && !gears.isEmpty()) {
                criteriaBuilder.and(createPredicateByList(gears, "gear", root, criteriaBuilder));
            }


            if (colors != null && !colors.isEmpty()) {
                criteriaBuilder.and(createPredicateByList(colors, "color", root, criteriaBuilder));
            }


            if (mileages != null && !mileages.isEmpty()) {
                criteriaBuilder.and(createPredicateByList(mileages, "mileage", root, criteriaBuilder));
            }


            if (performances != null && !performances.isEmpty()) {
                criteriaBuilder.and(createPredicateByList(performances, "performance", root, criteriaBuilder));
            }

            if (maxEngineCapacity != null && minEngineCapacity != null) {
                criteriaBuilder.and(createPredicateBetween(minEngineCapacity, maxEngineCapacity, "engineCapacity", root, criteriaBuilder));
            }

            if (maxEnginePower != null && minEnginePower != null) {
                criteriaBuilder.and(createPredicateBetween(minEnginePower, maxEnginePower, "enginePower", root, criteriaBuilder));
            }

            return criteriaBuilder.conjunction();
        };
        List<Car> carsNoPrice = carRepository.findAll(querySpec);
        List<Long> ids = carsNoPrice.stream().map(Car::getId).toList();
        List<Car> cars;
        Long left = minPrice == null ? 0 : minPrice;
        Long right = maxPrice == null ? Long.MAX_VALUE : maxPrice;
        if (state == null) {
            cars = carRepository.findByIdInAndAnnouncement_PriceBetween(
                    ids, left, right
            );
        } else {
            cars = carRepository.findByIdInAndAnnouncement_PriceBetweenAndState(
                    ids, left, right, state
            );
        }
        return cars;
    }

    List<Car> findDetailsByParameters(

    ) {
        Specification<Detail> querySpec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
//                if (!brand.isEmpty()) {
//                    predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
//                }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return detailRepository.findAll(querySpec);
    }

    public List<CarDTO> getCarsAnnouncementsDTOByCarAnnouncementsRequestDTO(
            @RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO
    ){
        log.info("request = {}", carAnnouncementsRequestDTO);
        List<Car> cars = this.findCarsByParameters(
                carAnnouncementsRequestDTO.getBrand(),
                carAnnouncementsRequestDTO.getModel(),
                carAnnouncementsRequestDTO.getTransmission(),
                carAnnouncementsRequestDTO.getGear(),
                carAnnouncementsRequestDTO.getMinEngineCapacity(),
                carAnnouncementsRequestDTO.getMaxEngineCapacity(),
                carAnnouncementsRequestDTO.getMinEnginePower(),
                carAnnouncementsRequestDTO.getMaxEnginePower(),
                carAnnouncementsRequestDTO.getColor(),
                carAnnouncementsRequestDTO.getMileage(),
                carAnnouncementsRequestDTO.getPerformance(),
                carAnnouncementsRequestDTO.getMinPrice(),
                carAnnouncementsRequestDTO.getMaxPrice(),
                carAnnouncementsRequestDTO.getState()
        );

        log.info("get cars = {}", cars);
        return cars.stream().map(car -> Mappers.getMapper(DTOMapper.class).carToCarDto(car)).toList();
    }

    public List<Car> getCarsAnnouncementsByCarAnnouncementsRequestDTO(
            @RequestBody CarAnnouncementsRequestDTO carAnnouncementsRequestDTO
    ){
        log.info("request = {}", carAnnouncementsRequestDTO);
        List<Car> cars = this.findCarsByParameters(
                carAnnouncementsRequestDTO.getBrand(),
                carAnnouncementsRequestDTO.getModel(),
                carAnnouncementsRequestDTO.getTransmission(),
                carAnnouncementsRequestDTO.getGear(),
                carAnnouncementsRequestDTO.getMinEngineCapacity(),
                carAnnouncementsRequestDTO.getMaxEngineCapacity(),
                carAnnouncementsRequestDTO.getMinEnginePower(),
                carAnnouncementsRequestDTO.getMaxEnginePower(),
                carAnnouncementsRequestDTO.getColor(),
                carAnnouncementsRequestDTO.getMileage(),
                carAnnouncementsRequestDTO.getPerformance(),
                carAnnouncementsRequestDTO.getMinPrice(),
                carAnnouncementsRequestDTO.getMaxPrice(),
                carAnnouncementsRequestDTO.getState()
        );

        log.info("get cars = {}", cars);
        return cars;
    }
}
