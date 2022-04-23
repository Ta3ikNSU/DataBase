package ta3ikdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta3ikdb.entities.User;
import ta3ikdb.repositories.UserRepository;

import java.util.Optional;

@Service
public class ValidationService {

    @Autowired
    UserRepository userRepository;

    public boolean validateRegister(String mail){
        Optional<User> userOptional = userRepository.findByMail(mail);
        return userOptional.isEmpty();
    }
}
