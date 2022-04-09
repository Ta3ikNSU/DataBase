package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("brand")
    @Getter
    String brand;
    
    @JsonProperty("model")
    @Getter
    String model;

    @JsonProperty("transmission")
    @Getter
    Integer transmission;

    @JsonProperty("gear")
    @Getter
    Integer gear;

    @JsonProperty("minEngineCapacity")
    @Getter
    Integer minEngineCapacity;

    @JsonProperty("maxEngineCapacity")
    @Getter
    Integer maxEngineCapacity;

    @JsonProperty("minEnginePower")
    @Getter
    Integer minEnginePower;

    @JsonProperty("maxEnginePower")
    @Getter
    Integer maxEnginePower;

    @JsonProperty("color")
    @Getter
    Integer color;

    @JsonProperty("mileage")
    @Getter
    String mileage;

    @JsonProperty("performance")
    @Getter
    Integer performance;

}
