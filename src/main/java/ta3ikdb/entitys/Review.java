package ta3ikdb.entitys;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long authorId;

    @Column
    private Integer grade;

    @ManyToOne
    private Car car;

    public Review(Long authorId, Integer grade, Car car) {
        this.authorId = authorId;
        this.grade = grade;
        this.car = car;
    }
}