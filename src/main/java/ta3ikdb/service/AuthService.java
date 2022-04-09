package ta3ikdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta3ikdb.entitys.Profile;
import ta3ikdb.entitys.User;
import ta3ikdb.repositories.ProfileRepository;
import ta3ikdb.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    public boolean auth(String mail, String password) {
        Optional<User> optionalUser = userRepository.findByMail(mail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getPassword().equals(password);
        }
        return false;
    }


    public boolean register(String mail, String password) {
        userRepository.save(new User(mail, password));
        profileRepository.save(new Profile(mail, mail));
        return true;
    }
}
