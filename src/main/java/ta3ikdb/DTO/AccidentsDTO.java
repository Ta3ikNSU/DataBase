package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@JsonDeserialize(builder = AccidentsDTO.AccidentsDTOBuilder.class)
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
@Builder(toBuilder = true)
public class AccidentsDTO {
    @JsonProperty
    List<AccidentDTO> accidentDTOs;
}
