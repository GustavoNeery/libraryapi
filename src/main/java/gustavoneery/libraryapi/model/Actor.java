package gustavoneery.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "born_date", nullable = false)
    private LocalDate bornDate;

    @Column(length = 50, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "actor")
    private List<Book> books;
}
