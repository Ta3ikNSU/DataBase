package ta3ikdb.controller;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ta3ikdb.DTO.AuthRequestDTO;
import ta3ikdb.DTO.ProfileDTO;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    // авторизация
    @PostMapping("/auth")
    public ProfileDTO auth(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("auth request = {}", authRequestDTO);
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        boolean isSuccess = authService.auth(mail, password);
        log.info("auth response : {}", isSuccess);
        return getProfileDTO(mail);
    }

    // регистрация
    @PostMapping("/register")
    public ProfileDTO register(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("Register auth request : {}", authRequestDTO);
        String mail = authRequestDTO.getMail();
        String password = authRequestDTO.getPassword();
        if (validationService.validateRegister(mail) && authService.register(mail, password)) {
            return getProfileDTO(mail);
        }
        return ProfileDTO.builder()
                .mail(null)
                .nickname(null)
                .role(null)
                .build();
    }

    @Nullable
    private ProfileDTO getProfileDTO(String mail) {
        ProfileDTO profileDTO = jdbcTemplate.query("select  mail, nickname, role from profile where mail = ?", new Object[]{mail}, rs -> {
            if (!rs.next()) {
                return ProfileDTO.builder()
                        .mail(null)
                        .nickname(null)
                        .role(null)
                        .build();
            }
            return ProfileDTO.builder()
                    .mail(rs.getString(1))
                    .nickname(rs.getString(2))
                    .role(rs.getString(3))
                    .build();
        });
        return profileDTO;
    }
}
