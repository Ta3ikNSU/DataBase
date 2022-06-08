package ta3ikdb.service;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ta3ikdb.entities.Profile;
import ta3ikdb.entities.User;
import ta3ikdb.repositories.ProfileRepository;
import ta3ikdb.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean validateRegister(String mail){
//        Optional<User> userOptional = userRepository.findByMail(mail);
        Integer count = jdbcTemplate.queryForObject("select count(*) from user_lk where mail = ?", Integer.class, mail);
        return !count.equals(1);
    }

    public boolean isUserOwnerAnnouncement(String mail, Long announcement_id){
        Optional<Profile> optionalProfile = profileRepository.findByMail(mail);
        if(optionalProfile.isPresent()){
            Long id = optionalProfile.get().getId();
            String sql = "select count(*) from profile_announcements_car where announcements_car_id = ? and profile_id = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, announcement_id, id);
            return count == 1;
        }
        return false;
    }
}
