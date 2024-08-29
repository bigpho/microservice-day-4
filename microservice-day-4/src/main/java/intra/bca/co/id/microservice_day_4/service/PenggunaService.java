package intra.bca.co.id.microservice_day_4.service;

import intra.bca.co.id.microservice_day_4.entity.Pengguna;
import intra.bca.co.id.microservice_day_4.repository.PenggunaRepository;
import intra.bca.co.id.microservice_day_4.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenggunaService implements UserDetailsService {
    @Autowired
    private PenggunaRepository penggunaRepository;

    public Pengguna getByUsername(String username) {return penggunaRepository.findByUsername(username);}

    public List<Pengguna> getUsers() {return penggunaRepository.findAll();}

    public Pengguna save(Pengguna user) {return penggunaRepository.save(user);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Pengguna pengguna = penggunaRepository.findByUsername(username);
        return new UserInfoDetails(pengguna);
    }

}
