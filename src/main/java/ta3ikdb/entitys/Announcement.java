package ta3ikdb.entitys;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "region")
    private Integer region;

    @Column(name = "date")
    private Instant date;

    @Column(name = "status")
    private Integer status;

    @Column(name = "price")
    private Long price;

}