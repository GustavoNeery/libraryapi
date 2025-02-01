package gustavoneery.libraryapi.repository;

import gustavoneery.libraryapi.model.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ActorRepositoryTest {

    @Autowired
    private ActorRepository repository;

    @Test
    public void testSave(){
        Actor actor = new Actor();
        actor.setName("Luiz");
        actor.setBornDate(LocalDate.of(1999, 11, 6));
        actor.setNationality("Brasileiro");

        Actor savedActor = repository.save(actor);
        System.out.println("Actor created: " + savedActor);
    }
}
