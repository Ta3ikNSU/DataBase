package ta3ikdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accident")
public class AccidentController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Рейтинг автомобилей по количеству дтп
    // Критерий Outer Join
    @GetMapping("/top")
    List<Object[]> getAccidentTop() {

        String sql = "select " +
                "c.brand as brand, " +
                "c.model as model, " +
                "count(*) as count " +
                "from car c left outer join accident_cars " +
                "on c.id = accident_cars.cars_id " +
                "group by c.brand, c.model";
        return jdbcTemplate.queryForList(sql)
                .stream()
                .map(row -> {
                    Object[] array = new Object[3];
                    array[0] = row.get("brand");
                    array[1] = row.get("model");
                    array[2] = row.get("count");
                    return array;
                })
                .toList();
    }
}
