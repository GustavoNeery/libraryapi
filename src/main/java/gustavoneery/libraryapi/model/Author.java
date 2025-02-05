package gustavoneery.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "born_date", nullable = false)
    private LocalDate bornDate;

    @Column(length = 50, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @CreatedDate
    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

    @LastModifiedDate
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "user_id")
    private UUID userId;

    public void setName(String name) {
        this.name = name;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setDateRegistration(LocalDateTime dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public String getNationality() {
        return nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public LocalDateTime getDateRegistration() {
        return dateRegistration;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getId() {
        return this.id;
    }
}
