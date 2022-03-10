package ta3ikdb.entitys;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "registerdate", nullable = false)
    private Timestamp registerdate;

    @Column(name = "nickname", nullable = false, length = 36)
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "favoriteannouncementid")
    private List<OldAnnouncement> favoriteAnnouncement = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "old_announcementids")
    private List<OldAnnouncement> oldAnnouncements = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "announcementids")
    private List<Announcement> Announcement = new ArrayList<>();
}