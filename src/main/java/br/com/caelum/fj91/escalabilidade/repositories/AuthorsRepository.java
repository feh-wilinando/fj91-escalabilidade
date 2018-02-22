package br.com.caelum.fj91.escalabilidade.repositories;

import br.com.caelum.fj91.escalabilidade.models.Author;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AuthorsRepository extends Repository<Author, String> {
    Iterable<Author> findAll();

    Optional<Author> findByName(String name);

    Author save(Author author);

    boolean exists(String name);

    void delete(String name);
}
