package ta3ikdb.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review", schema = "public")
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

    @ManyToOne
    private Car car;

    public Review(Long authorId, Integer grade,Car car) {
        this.authorId = authorId;
        this.grade = grade;
        this.car = car;
    }
}