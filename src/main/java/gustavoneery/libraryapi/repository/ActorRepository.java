package gustavoneery.libraryapi.repository;

import gustavoneery.libraryapi.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {
}
