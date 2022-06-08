package ta3ikdb.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ta3ikdb.entities.Car;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = ProfileDTO.ProfileDTOBuilder.class)
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
@Builder(toBuilder = true)
public class ProfileDTO {

    @JsonProperty("mail")
    String mail;

    @JsonProperty("nickname")
    String nickname;

    @JsonProperty("role")
    String role;
}
