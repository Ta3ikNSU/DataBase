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
public class Car {
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
    private String brand;

    @Column
    private String model;

    @Column
    private Integer transmission;

    @Column
    private Integer gear;

    @Column
    private Integer engineCapacity;

    @Column
    private Integer enginePower;

    @Column
    private String color;

    @Column
    private String mileage;

    @Column
    private Integer performance;

    @Column
    private Long vinNumber;

    @Column
    private String description;
}