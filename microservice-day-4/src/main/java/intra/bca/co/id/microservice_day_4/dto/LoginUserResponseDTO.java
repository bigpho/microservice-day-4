package intra.bca.co.id.microservice_day_4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResponseDTO {
    private String jwtToken;

}
