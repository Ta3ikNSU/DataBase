package ta3ikdb.entitys;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

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
    private Integer status;

    @Column
    private Long price;

}