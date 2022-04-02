package ta3ikdb.entitys;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    private Announcement announcement;

    @OneToMany
    @Column
    private List<Announcement> oldAnnouncement = new ArrayList<>();

    @Column
    private Integer type;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String status;
}