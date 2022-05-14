package ta3ikdb.entities;

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
@ToString
public class Detail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Announcement announcement;

    @Column
    private Integer type;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String status;



    public Detail(Announcement announcement, Integer type, String brand, String model, String status) {
        this.announcement = announcement;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.status = status;
    }
}