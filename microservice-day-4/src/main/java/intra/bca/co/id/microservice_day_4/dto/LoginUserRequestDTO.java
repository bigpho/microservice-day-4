package intra.bca.co.id.microservice_day_4.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserRequestDTO {
    private String username;
    private String password;
}
