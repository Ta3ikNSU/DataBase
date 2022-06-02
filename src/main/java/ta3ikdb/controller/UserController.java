package ta3ikdb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.*;
import ta3ikdb.DTO.OkResponseDTO;
import ta3ikdb.entities.Announcement;
import ta3ikdb.entities.Profile;
import ta3ikdb.repositories.AnnouncementRepository;
import ta3ikdb.repositories.ProfileRepository;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    AnnouncementRepository announcementRepository;


    @PostMapping(value = "/{mail}/{announcement_id}")
    public OkResponseDTO addFavoriteAnnouncement(@PathVariable String mail, @PathVariable String announcement_id) {
        log.info("add {}:{}", mail, announcement_id);
        Optional<Profile> optionalProfile = profileRepository.findByMail(mail);

        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(Long.valueOf(announcement_id));

        if (optionalProfile.isEmpty()) {
            return new OkResponseDTO(false);
        }
        if (optionalAnnouncement.isEmpty()) {
            return new OkResponseDTO(false);
        }
        Long profileId = optionalProfile.get().getId();
        Long announcementId = optionalAnnouncement.get().getId();
        String sql = "insert into profile_favorite_announcement_car (profile_id, favorite_announcement_car_id) values (?, ?) ";
        jdbcTemplate.update(sql, profileId, announcementId);
        return new OkResponseDTO(true);
    }

    @DeleteMapping(value = "/{mail}/{announcement_id}")
    public OkResponseDTO deleteFavoriteAnnouncement(@PathVariable String mail, @PathVariable String announcement_id) {
        log.info("delete {}:{}", mail, announcement_id);
        Optional<Profile> optionalProfile = profileRepository.findByMail(mail);

        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(Long.valueOf(announcement_id));

        if (optionalProfile.isEmpty()) {
            return new OkResponseDTO(false);
        }
        if (optionalAnnouncement.isEmpty()) {
            return new OkResponseDTO(false);
        }
        Long profileId = optionalProfile.get().getId();
        Long announcementId = optionalAnnouncement.get().getId();
        String sql = "delete from profile_favorite_announcement_car where profile_id = ? and favorite_announcement_car_id = ?";
        jdbcTemplate.update(sql, profileId, announcementId);
        return new OkResponseDTO(true);
    }
}
