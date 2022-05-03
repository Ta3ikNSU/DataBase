package ta3ikdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ta3ikdb.DTO.AuthRequestDTO;
import ta3ikdb.DTO.OkResponseDTO;
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
    public OkResponseDTO auth(@RequestBody AuthRequestDTO authRequestDTO) {
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        return new OkResponseDTO(authService.auth(mail, password));
    }

    @PostMapping("/register")
    public OkResponseDTO register(@RequestBody AuthRequestDTO authRequestDTO) {
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        if (!validationService.validateRegister(mail)) {
            return new OkResponseDTO(false);
        }
        return new OkResponseDTO(authService.register(mail, password));
    }
}
