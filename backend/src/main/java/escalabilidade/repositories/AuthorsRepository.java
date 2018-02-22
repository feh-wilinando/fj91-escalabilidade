package escalabilidade.repositories;

import escalabilidade.models.Author;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AuthorsRepository extends Repository<Author, String> {
    Iterable<Author> findAll();

    Optional<Author> findByName(String name);

    Author save(Author author);

    boolean exists(String name);

    void delete(String name);
}
