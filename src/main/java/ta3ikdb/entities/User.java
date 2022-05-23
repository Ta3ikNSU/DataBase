package ta3ikdb.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user_lk", schema = "public")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(unique = true, nullable = false)
    private String mail;
    @Column(nullable = false)
    private String password;

    // Критерий 1 : 1
    @JoinColumn
    @OneToOne
    private Profile profile;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }
}
