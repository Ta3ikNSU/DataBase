package ta3ikdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ta3ikdb.DTO.AuthRequestDTO;
import ta3ikdb.DTO.AuthResponseDTO;
import ta3ikdb.DTO.RegisterResponseDTO;
import ta3ikdb.service.AuthService;
import ta3ikdb.service.ValidationService;

@RestController
@RequestMapping(value = "/authorize")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    ValidationService validationService;

    @PostMapping("/auth")
    public AuthResponseDTO auth(@RequestBody AuthRequestDTO authRequestDTO) {
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        return new AuthResponseDTO(authService.auth(mail, password));
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody AuthRequestDTO authRequestDTO) {
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        if (!validationService.validateRegister(mail)) {
            return new RegisterResponseDTO(false);
        }
        return new RegisterResponseDTO(authService.register(mail, password));
    }
}
