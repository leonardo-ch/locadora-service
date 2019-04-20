package app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenUserObject {
    @JsonProperty("iss")
    private String iss;

    @JsonProperty("exp")
    private String exp;

    @JsonProperty("iat")
    private String iat;

    @JsonProperty("userId")
    private String userId;
}
