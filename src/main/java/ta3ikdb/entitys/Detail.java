package ta3ikdb.entitys;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "DETAIL")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "announcementid")
    private Announcement announcement;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "old_announcementid")
    private List<OldAnnouncement> oldAnnouncement = new ArrayList<>();

    @Column(name = "type")
    private Integer type;

    @Column(name = "brand", length = 36)
    private String brand;

    @Column(name = "model", length = 36)
    private String model;

    @Column(name = "status")
    private String status;
}