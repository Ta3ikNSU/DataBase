package ta3ikdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ta3ikdb.entitys.Car;
import ta3ikdb.entitys.Detail;
import ta3ikdb.repositories.CarRepository;
import ta3ikdb.repositories.DetailRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementFinderService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    DetailRepository detailRepository;

    public List<Car> findCarsByParameters(
            String brand,
            String model,
            Integer transmission,
            Integer gear,
            Integer minEngineCapacity,
            Integer maxEngineCapacity,
            Integer minEnginePower,
            Integer maxEnginePower,
            Integer color,
            String mileage,
            Integer performance
    ) {
        Specification<Car> querySpec = new Specification<Car>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!brand.isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
                }
                if (!model.isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("model"), model));
                }
                if (transmission != null) {
                    predicates.add(criteriaBuilder.equal(root.get("transmission"), transmission));
                }
                if (gear != null) {
                    predicates.add(criteriaBuilder.equal(root.get("gear"), gear));
                }
                if (minEngineCapacity != null) {
                    predicates.add(criteriaBuilder.equal(root.get("minEngineCapacity"), minEngineCapacity));
                }
                if (maxEngineCapacity != null) {
                    predicates.add(criteriaBuilder.equal(root.get("maxEngineCapacity"), maxEngineCapacity));
                }
                if (minEnginePower != null) {
                    predicates.add(criteriaBuilder.equal(root.get("minEnginePower"), minEnginePower));
                }
                if (maxEnginePower != null) {
                    predicates.add(criteriaBuilder.equal(root.get("maxEnginePower"), maxEnginePower));
                }
                if (color != null) {
                    predicates.add(criteriaBuilder.equal(root.get("color"), color));
                }
                if (!mileage.isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("mileage"), mileage));
                }
                if (performance != null) {
                    predicates.add(criteriaBuilder.equal(root.get("performance"), performance));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return carRepository.findAll(querySpec);
    }

    List<Car> findDetailsByParameters(

    ) {
        Specification<Detail> querySpec = new Specification<Detail>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
//                if (!brand.isEmpty()) {
//                    predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
//                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return detailRepository.findAll(querySpec);
    }
}
