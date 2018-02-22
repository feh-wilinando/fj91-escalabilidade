package br.com.caelum.fj91.escalabilidade.repositories;

import br.com.caelum.fj91.escalabilidade.models.Book;
import org.springframework.data.repository.Repository;

import java.util.Optional;


public interface BooksRepository extends Repository<Book, Long> {

    Iterable<Book> findAll();

    Optional<Book> findById(Long id);

    boolean exists(Long id);

    void delete(Long id);

    Book save(Book book);
}
