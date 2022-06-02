package ta3ikdb.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ta3ikdb.entities.Profile;
import ta3ikdb.entities.User;
import ta3ikdb.repositories.ProfileRepository;
import ta3ikdb.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@EnableTransactionManagement
@Slf4j
public class AuthService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    public boolean auth(String mail, String password) {
        Optional<User> optionalUser = userRepository.findByMail(mail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
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
        userRepository.save(new User(mail, password));
        profileRepository.save(new Profile(mail, mail));
        log.info("user = {}, {} success register", mail, password);
        return true;
    }
}
