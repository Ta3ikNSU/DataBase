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
public class Car implements Comparable<Car> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Announcement announcement;

    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<Announcement> oldAnnouncement = new ArrayList<>();

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column
    private String transmission;

    @Column
    private String gear;

    @Column
    private Integer engineCapacity;

    @Column
    private Integer enginePower;

    @Column
    private String color;

    @Column
    private Integer mileage;

    @Column
    private String performance;

    @Column(nullable = false)
    private Long vinNumber;

    @Column
    private String description;

    public Car(Announcement announcement, String brand, String model, String transmission, String gear, Integer engineCapacity, Integer enginePower, String color, Integer mileage, String performance, Long vinNumber, String description) {
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

    @Override
    public int compareTo(Car other) {
        return (int)(other.vinNumber - this.vinNumber);
    }
}