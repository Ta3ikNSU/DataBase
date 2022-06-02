package ta3ikdb.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    ValidationService validationService;

    // авторизация
    @PostMapping("/auth")
    public OkResponseDTO auth(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("auth request = {}", authRequestDTO);
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        boolean isSuccess = authService.auth(mail, password);
        log.info("auth response : {}", isSuccess);
        return new OkResponseDTO(isSuccess);
    }

    // регистрация
    @PostMapping("/register")
    public OkResponseDTO register(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("Register auth request : {}", authRequestDTO);
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        if (!validationService.validateRegister(mail)) {
            return new OkResponseDTO(false);
        }
        return new OkResponseDTO(authService.register(mail, password));
    }
}
