package intra.bca.co.id.microservice_day_4.repository;

import intra.bca.co.id.microservice_day_4.entity.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
    Pengguna findByUsername(String username);

}
