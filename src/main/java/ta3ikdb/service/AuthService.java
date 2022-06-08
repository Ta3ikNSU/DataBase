package ta3ikdb.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ta3ikdb.entities.Profile;
import ta3ikdb.entities.User;
import ta3ikdb.repositories.ProfileRepository;
import ta3ikdb.repositories.UserRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@EnableTransactionManagement
@Slf4j
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean auth(String mail, String password) {
        User user = jdbcTemplate.query("select mail, password from user_lk where mail = ?", new Object[]{mail}, rs -> {
            if(!rs.next()){
                return null;
            }
            String user_mail = rs.getString(1);
            String user_password = rs.getString(2);
            return new User(null, user_mail, user_password, null);
        });
        if (user != null) {
            boolean isSuccess = user.getPassword().equals(password);
            if(isSuccess){
                log.info("user = {}, {} success auth", mail, password);
            } else {
                log.info("user = {}, {} enter incorrect password", mail, password);
            }
            return isSuccess;
        } else {
            log.error("user = {}, {} not exist", mail, password);
        }
        return false;
    }


    @Transactional
    public boolean register(String mail, String password) {
        jdbcTemplate.update("insert into profile (mail, nickname, register_date, role) values (?,?,?,?)", mail, mail, new Timestamp(System.currentTimeMillis()), "USER");
        Long id = jdbcTemplate.queryForObject("select id from profile where mail = ?", Long.class, mail);
        jdbcTemplate.update("insert into user_lk (mail, password, profile_id) values (?,?,?)", mail, password, id);
        log.info("user = {}, {} success register", mail, password);
        return true;
    }
}
