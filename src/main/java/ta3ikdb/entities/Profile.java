package ta3ikdb.entities;

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
    @Column
    private Long id;
    @Column(nullable = false)
    private Timestamp register_date;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String nickname;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Car> favoriteAnnouncementCar = new ArrayList<>();

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Car> oldAnnouncementsCar = new ArrayList<>();

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Car> announcementsCar = new ArrayList<>();

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Detail> favoriteAnnouncementDetail = new ArrayList<>();

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Detail> oldAnnouncementsDetail = new ArrayList<>();

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Detail> announcementsDetail = new ArrayList<>();

    public Profile(String nickname, String mail) {
        this.nickname = nickname;
        this.mail = mail;
        this.register_date = new Timestamp(System.currentTimeMillis());
    }
}