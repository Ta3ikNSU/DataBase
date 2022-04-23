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

    private <T> void addAndPredicateByList(List<T> values, String name,
                                           Root<Car> root, CriteriaBuilder criteriaBuilder) {
        if (values != null && !values.isEmpty()) {
            List<Predicate> predicates = values.stream()
                    .map(value -> criteriaBuilder.equal(root.get(name), value))
                    .toList();
            criteriaBuilder.and(criteriaBuilder.or(predicates.toArray(Predicate[]::new)));
        }
    }

    private void addPredicateBetween(Integer left, Integer right, String name,
                                     Root<Car> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (left == null) {
            predicates.add(criteriaBuilder.greaterThan(root.get(name), Integer.MIN_VALUE));
        } else {
            predicates.add(criteriaBuilder.greaterThan(root.get(name), left));
        }
        if (right == null) {
            predicates.add(criteriaBuilder.lessThan(root.get(name), Integer.MAX_VALUE));
        } else {
            predicates.add(criteriaBuilder.greaterThan(root.get(name), right));
        }
        criteriaBuilder.and(criteriaBuilder.or(predicates.toArray(Predicate[]::new)));
    }

    public List<Car> findCarsByParameters(
            List<String> brands,
            List<String> models,
            List<Integer> transmissions,
            List<Integer> gears,
            Integer minEngineCapacity,
            Integer maxEngineCapacity,
            Integer minEnginePower,
            Integer maxEnginePower,
            List<Integer> colors,
            List<String> mileages,
            List<Integer> performances
    ) {

        Specification<Car> querySpec = (root, query, criteriaBuilder) -> {
            addAndPredicateByList(brands, "brand", root, criteriaBuilder);
            addAndPredicateByList(models, "model", root, criteriaBuilder);
            addAndPredicateByList(transmissions, "transmission", root, criteriaBuilder);
            addAndPredicateByList(gears, "gear", root, criteriaBuilder);
            addAndPredicateByList(colors, "color", root, criteriaBuilder);
            addAndPredicateByList(mileages, "mileage", root, criteriaBuilder);
            addAndPredicateByList(performances, "performance", root, criteriaBuilder);
            addPredicateBetween(minEngineCapacity, maxEngineCapacity, "engineCapacity", root, criteriaBuilder);
            addPredicateBetween(minEnginePower, maxEnginePower, "enginePower", root, criteriaBuilder);

            return criteriaBuilder.conjunction();
        };
        return carRepository.findAll(querySpec);
    }

    List<Car> findDetailsByParameters(

    ) {
        Specification<Detail> querySpec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return detailRepository.findAll(querySpec);
    }
}
