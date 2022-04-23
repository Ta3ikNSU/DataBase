package ta3ikdb.entitys;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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


    public Announcement(Integer region, AnnouncementState status, Long price) {
        this.region = region;
        this.status = status;
        this.price = price;
        this.start_date = new Timestamp(System.currentTimeMillis());
    }
}