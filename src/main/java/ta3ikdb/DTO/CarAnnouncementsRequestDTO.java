package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
public class CarAnnouncementsRequestDTO {
    private String brand;
    private String model;
    private Integer transmission;
    private Integer gear;
    private Integer minEngineCapacity;
    private Integer maxEngineCapacity;
    private Integer minEnginePower;
    private Integer maxEnginePower;
    private Integer color;
    private String mileage;
    private Integer performance;
}
