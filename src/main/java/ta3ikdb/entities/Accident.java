package ta3ikdb.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accident", schema = "public")
@ToString
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    @ManyToMany
    List<Car> cars;

    public Accident(List<Car> cars){
        this.cars.addAll(cars);
    }
}
