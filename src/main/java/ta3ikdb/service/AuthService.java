package ta3ikdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta3ikdb.entitys.User;
import ta3ikdb.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public boolean auth(String mail, String password) {
        Optional<User> optionalUser = userRepository.findByMail(mail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getPassword().equals(password);
        }
        return false;
    }


    public boolean register(String mail, String password) {
        if (userRepository.findByMail(mail).isEmpty()) {
            userRepository.save(new User(mail, password));
            return true;
        }
        return false;
    }
}
