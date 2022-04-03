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
    private Integer color;

    @Column
    private String mileage;

    @Column
    private Integer performance;

    @Column
    private Long vinNumber;

    @Column
    private String description;

    public Car(Announcement announcement,
               String brand,
               String model,
               Integer transmission,
               Integer gear,
               Integer engineCapacity,
               Integer enginePower,
               Integer color,
               String mileage,
               Integer performance,
               Long vinNumber,
               String description
    ) {
        this.announcement = announcement;
        this.brand = brand;
        this.model = model;
        this.transmission = transmission;
        this.gear = gear;
        this.engineCapacity = engineCapacity;
        this.enginePower = enginePower;
        this.color = color;
        this.mileage = mileage;
        this.performance = performance;
        this.vinNumber = vinNumber;
        this.description = description;
    }
}