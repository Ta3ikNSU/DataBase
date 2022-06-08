package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonDeserialize(builder = CreateCarAnnouncementsRequestDTO.CreateCarAnnouncementsRequestDTOBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@ToString
public class CreateCarAnnouncementsRequestDTO {
        @JsonProperty("mail")
        @Getter
        String mail;

        @JsonProperty("brand")
        @Getter
        String brand;

        @JsonProperty("model")
        @Getter
        String model;

        @JsonProperty("transmission")
        @Getter
        String transmission;

        @JsonProperty("gear")
        @Getter
        String gear;

        @JsonProperty("engineCapacity")
        @Getter
        Integer engineCapacity;

        @JsonProperty("enginePower")
        @Getter
        Integer enginePower;

        @JsonProperty("color")
        @Getter
        String color;

        @JsonProperty("mileage")
        @Getter
        Integer mileage;

        @JsonProperty("performance")
        @Getter
        String performance;

        @JsonProperty("vinNumber")
        @Getter
        Long vinNumber;

        @JsonProperty("description")
        @Getter
        String description;

        @JsonProperty("region")
        @Getter
        Integer region;

        @JsonProperty("price")
        @Getter
        Long price;
}