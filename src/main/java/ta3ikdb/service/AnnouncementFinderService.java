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
import java.util.stream.Collectors;

@Service
public class AnnouncementFinderService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    DetailRepository detailRepository;

    private <T> Predicate createPredicateByList(List<T> values, String name,
                                                Root<Car> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (values != null && !values.isEmpty()) {
            predicates = values.stream()
                    .map((value) -> criteriaBuilder.equal(root.get("name"), value))
                    .collect(Collectors.toList());
        }
        return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
    }

    private Predicate createPredicateBetween(Integer left, String leftName, Integer right, String rightName,
                                             Root<Car> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (left != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get(leftName), left));
        }
        if (right != null) {
            predicates.add(criteriaBuilder.lessThan(root.get(rightName), right));
        }
        return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
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
        Specification<Car> querySpec = (root, query, criteriaBuilder) -> criteriaBuilder.and(
                createPredicateByList(brands, "brand", root, criteriaBuilder),
                createPredicateByList(models, "models", root, criteriaBuilder),
                createPredicateByList(transmissions, "transmission", root, criteriaBuilder),
                createPredicateByList(gears, "gear", root, criteriaBuilder),
                createPredicateBetween(minEngineCapacity, "minEngineCapacity",
                        maxEngineCapacity, "maxEngineCapacity", root, criteriaBuilder),
                createPredicateBetween(minEnginePower, "minEnginePower",
                        maxEnginePower, "maxEnginePower", root, criteriaBuilder),
                createPredicateByList(colors, "color", root, criteriaBuilder),
                createPredicateByList(mileages, "mileage", root, criteriaBuilder),
                createPredicateByList(performances, "performance", root, criteriaBuilder)
        );
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
