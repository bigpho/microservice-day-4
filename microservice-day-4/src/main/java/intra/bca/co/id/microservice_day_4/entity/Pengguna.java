package intra.bca.co.id.microservice_day_4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pengguna")
public class Pengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;

}
