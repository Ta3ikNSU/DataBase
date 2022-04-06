package ta3ikdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ta3ikdb.DTO.AuthRequestDTO;
import ta3ikdb.DTO.AuthResponseDTO;
import ta3ikdb.DTO.RegisterResponseDTO;
import ta3ikdb.entitys.User;
import ta3ikdb.repositories.UserRepository;
import ta3ikdb.service.AuthService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping
    public AuthResponseDTO auth(@RequestBody AuthRequestDTO authRequestDTO ) {
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        return new AuthResponseDTO(authService.auth(mail, password));
    }

    @PostMapping("/register")
    public RegisterResponseDTO register( @RequestBody AuthRequestDTO authRequestDTO) {
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();

        return new RegisterResponseDTO(authService.register(mail, password));
    }
}
