package ta3ikdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ta3ikdb.repositories.CarRepository;

@RestController
@RequestMapping("/car")
public class CarMarketController {

    @Autowired
    CarRepository carRepository;


}
