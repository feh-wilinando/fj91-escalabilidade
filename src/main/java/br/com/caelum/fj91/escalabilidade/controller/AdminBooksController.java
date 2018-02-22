package br.com.caelum.fj91.escalabilidade.controller;

import br.com.caelum.fj91.escalabilidade.helpers.BindingResultHelper;
import br.com.caelum.fj91.escalabilidade.models.Book;
import br.com.caelum.fj91.escalabilidade.models.dtos.BookDTO;
import br.com.caelum.fj91.escalabilidade.repositories.BooksRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("admin/books")
public class AdminBooksController {


    private final BooksRepository repository;
    private final BindingResultHelper resultHelper;

    public AdminBooksController(BooksRepository repository, BindingResultHelper resultHelper) {
        this.repository = repository;
        this.resultHelper = resultHelper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid BookDTO dto, BindingResult result){

       return resultHelper.hasErrorIn(result)
                .orElseGet(() -> {
                    Book book = repository.save(dto.toEntity());

                    URI uri = URI.create("/books/" + book.getId());

                    return ResponseEntity.created(uri).body(book);
                });

    }



    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upate(@RequestBody @Valid BookDTO dto, BindingResult result){

        return resultHelper.hasErrorIn(result).orElseGet( () -> {
            repository.save(dto.toEntity());
            return ResponseEntity.accepted().build();
        });
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id){

        if (repository.exists(id) ) {
            repository.delete(id);

            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.notFound().build();
    }



}
