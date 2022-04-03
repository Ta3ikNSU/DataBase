package ta3ikdb.entitys;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp register_date;

    @Column(nullable = false)
    private String nickname;

    @Column
    @OneToMany
    private List<Announcement> favoriteAnnouncement = new ArrayList<>();

    @Column
    @OneToMany
    private List<Announcement> oldAnnouncements = new ArrayList<>();

    @Column
    @OneToMany
    private List<Announcement> announcements = new ArrayList<>();

    public Profile(String nickname) {
        this.nickname = nickname;
        this.register_date = new Timestamp(System.currentTimeMillis());
    }
}