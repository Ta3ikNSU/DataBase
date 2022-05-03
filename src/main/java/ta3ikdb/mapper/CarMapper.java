package ta3ikdb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ta3ikdb.DTO.CarDTO;
import ta3ikdb.entities.Car;

@Mapper
public interface CarMapper {
    @Mapping(target = "price", source = "announcement.price")
    CarDTO carToCarDto(Car car);
}
