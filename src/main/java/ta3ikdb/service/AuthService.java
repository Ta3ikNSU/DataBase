package ta3ikdb.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta3ikdb.entities.Profile;
import ta3ikdb.entities.User;
import ta3ikdb.repositories.ProfileRepository;
import ta3ikdb.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final Logger log =  LogManager.getLogger();
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
                log.info("user = {},{} success auth", mail, password);
            } else {
                log.info("user = {},{} enter incorrect password", mail, password);
            }
        }
        log.error("user = {},{} not exist", mail, password);
        return false;
    }


    public boolean register(String mail, String password) {
        userRepository.save(new User(mail, password));
        profileRepository.save(new Profile(mail, mail));
        log.info("user = {},{} success register", mail, password);
        return true;
    }
}
