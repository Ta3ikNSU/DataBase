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

import java.sql.Timestamp;
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
public class AnnouncementDTO {

    @JsonProperty("region")
    Integer region;

    @JsonProperty("startDate")
    String startDate;

    @JsonProperty("endDate")
    String endDate;

    @JsonProperty("state")
    AnnouncementState status;

    @JsonProperty("price")
    Long price;

    @JsonProperty("photosList")
    String photosList;
}
