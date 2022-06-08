package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
@Builder
@JsonDeserialize(builder = CarDTO.CarDTOBuilder.class)
public class CarDTO {
    String brand;
    String model;
    String transmission;
    String gear;
    Integer engineCapacity;
    Integer enginePower;
    String  color;
    Integer mileage;
    String performance;
    Long vinNumber;
    String description;
    AnnouncementDTO announcementDTO;
}
