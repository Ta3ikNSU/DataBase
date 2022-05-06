package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ta3ikdb.entities.AnnouncementState;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
public class CarAnnouncementsRequestDTO {
    @JsonProperty("brand")
    @Getter
    List<String> brand;

    @JsonProperty("model")
    @Getter
    List<String> model;

    @JsonProperty("transmission")
    @Getter
    List<Integer> transmission;

    @JsonProperty("gear")
    @Getter
    List<Integer> gear;

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
    List<Integer> color;

    @JsonProperty("mileage")
    @Getter
    List<String> mileage;

    @JsonProperty("performance")
    @Getter
    List<Integer> performance;

    @JsonProperty("minPrice")
    @Getter
    Long minPrice;

    @JsonProperty("maxPrice")
    @Getter
    Long maxPrice;

    @JsonProperty("maxPrice")
    @Getter
    AnnouncementState state;

}