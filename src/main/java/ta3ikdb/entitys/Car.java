package ta3ikdb.entitys;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CAR")
public class Car {
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

    @Column(name = "brand", nullable = false, length = 36)
    private String brand;

    @Column(name = "model", nullable = false, length = 36)
    private String model;

    @Column(name = "transmission")
    private Integer transmission;

    @Column(name = "gear")
    private Integer gear;

    @Column(name = "engine_capacity")
    private Integer engineCapacity;

    @Column(name = "engine_power")
    private Integer enginePower;

    @Column(name = "color", length = 36)
    private String color;

    @Column(name = "mileage", length = 36)
    private String mileage;

    @Column(name = "performance")
    private Integer performance;

    @Column(name = "vin_number")
    private Long vinNumber;

    @Column(name = "description")
    private String description;
}