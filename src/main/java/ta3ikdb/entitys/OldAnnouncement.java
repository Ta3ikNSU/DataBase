package ta3ikdb.entitys;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@Entity
@Table(name = "OLD_ANNOUNCEMENT")
public class OldAnnouncement {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "region")
    private Integer region;

    @Column(name = "datestart")
    private Timestamp datestart;

    @Column(name = "dateend")
    private Timestamp dateend;

    @Column(name = "status")
    private Integer status;

    @Column(name = "price")
    private Long price;
}