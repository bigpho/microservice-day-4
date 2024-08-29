package intra.bca.co.id.microservice_day_4.controller;

import ch.qos.logback.core.util.StringUtil;
import intra.bca.co.id.microservice_day_4.dto.InsertUserRequestDTO;
import intra.bca.co.id.microservice_day_4.dto.LoginUserRequestDTO;
import intra.bca.co.id.microservice_day_4.dto.LoginUserResponseDTO;
import intra.bca.co.id.microservice_day_4.entity.Pengguna;
import intra.bca.co.id.microservice_day_4.service.JwtService;
import intra.bca.co.id.microservice_day_4.service.PenggunaService;
import intra.bca.co.id.microservice_day_4.utilities.EncoderUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.Authenticator;
import java.util.List;

@RestController
public class PenggunaController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private EncoderUtilities encoderUtilities;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Pengguna registerPengguna(@RequestBody InsertUserRequestDTO request) {
        Pengguna duplicateUser = penggunaService.getByUsername(request.getUsername());

        if (duplicateUser == null || !duplicateUser.getUsername().equals(request.getUsername())) {
            String encodedPassword = encoderUtilities.encoder().encode(request.getPassword());

            Pengguna pengguna = new Pengguna();
            pengguna.setUsername(request.getUsername());
            pengguna.setPassword(encodedPassword);
            return penggunaService.save(pengguna);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with the same id or username already exists");
    }

    @PostMapping("/api/auth/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginUserResponseDTO loginUser(@RequestBody LoginUserRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return new LoginUserResponseDTO(jwtService.generateToken(request.getUsername()));
        }
        throw new UsernameNotFoundException("Invalid user request!");
    }

    @GetMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Pengguna> getUsers() {
        return penggunaService.getUsers();
    }

}
