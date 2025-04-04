package gustavoneery.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "book")
@Data
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderBook genderBookr;

    @Column(precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    @CreatedDate
    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

    @LastModifiedDate
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;
}