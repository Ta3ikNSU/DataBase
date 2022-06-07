package ta3ikdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta3ikdb.entities.Announcement;
import ta3ikdb.entities.AnnouncementState;
import ta3ikdb.repositories.AnnouncementRepository;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    AnnouncementRepository announcementRepository;

    public void updateAnnouncementStateById(Long id, AnnouncementState announcementState){
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        if(optionalAnnouncement.isPresent()){
            Announcement announcement = optionalAnnouncement.get();
            announcement.setStatus(announcementState);
        }
    }
}
