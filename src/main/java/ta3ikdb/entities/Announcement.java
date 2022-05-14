package ta3ikdb.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private Integer region;

    @Column
    private Timestamp start_date;

    @Column
    private Timestamp close_date;

    @Column
    private AnnouncementState status;

    @Column
    private Long price;

    // json field for photos
    @Column
    private String photosList;

    public Announcement(Integer region, AnnouncementState status, Long price) {
        this.region = region;
        this.status = status;
        this.price = price;
        this.start_date = new Timestamp(System.currentTimeMillis());
    }
}