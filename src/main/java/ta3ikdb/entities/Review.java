package ta3ikdb.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review", schema = "public")
@ToString
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    private Long authorId;

    @Column
    private String description;

    @ManyToOne
    private Car car;

    public Review(Long authorId, String description,Car car) {
        this.authorId = authorId;
        this.description = description;
        this.car = car;
    }
}