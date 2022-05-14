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
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    private Long authorId;

    @Column
    private Integer grade;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Car> cars;

    public Review(Long authorId, Integer grade, List<Car> cars) {
        this.authorId = authorId;
        this.grade = grade;
        this.cars.addAll(cars);
    }
}