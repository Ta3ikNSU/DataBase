package ta3ikdb.mapper;

import org.mapstruct.Mapper;
import ta3ikdb.DTO.CarDTO;
import ta3ikdb.entities.Car;

@Mapper
public interface CarMapper {
    CarDTO carToCarDto(Car car);
}
