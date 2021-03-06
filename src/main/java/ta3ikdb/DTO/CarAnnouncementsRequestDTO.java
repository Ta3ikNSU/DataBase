package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ta3ikdb.entities.AnnouncementState;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonDeserialize(builder = CarAnnouncementsRequestDTO.CarAnnouncementsRequestDTOBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
@Builder
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
    List<String> color;

    @JsonProperty("mileage")
    @Getter
    List<Integer> mileage;

    @JsonProperty("performance")
    @Getter
    List<String> performance;

    @JsonProperty("minPrice")
    @Getter
    Long minPrice;

    @JsonProperty("maxPrice")
    @Getter
    Long maxPrice;

    @JsonProperty("state")
    @Getter
    AnnouncementState state;

    @JsonProperty("sort_name")
    @Getter
    String fieldSortName;
}