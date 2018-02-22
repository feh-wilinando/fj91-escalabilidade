package escalabilidade.controller;

import escalabilidade.helpers.BindingResultHelper;
import escalabilidade.models.Author;
import escalabilidade.models.Book;
import escalabilidade.repositories.AuthorsRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("admin/authors")
public class AdminAuthorsController {

    private final AuthorsRepository repository;
    private final BindingResultHelper resultHelper;

    public AdminAuthorsController(AuthorsRepository repository, BindingResultHelper resultHelper) {
        this.repository = repository;
        this.resultHelper = resultHelper;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid Author author, BindingResult result){

        return resultHelper.hasErrorIn(result)
                .orElseGet(() -> {
                    repository.save(author);

                    URI uri = URI.create("/authors/" + author.getName());

                    return ResponseEntity.created(uri).body(author);
                });

    }



    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upate(@RequestBody @Valid Author author, BindingResult result){

        return resultHelper.hasErrorIn(result).orElseGet( () -> {
            repository.save(author);
            return ResponseEntity.accepted().build();
        });
    }


    @DeleteMapping("{name}")
    public ResponseEntity<Book> deleteById(@PathVariable String name){

        if (repository.exists(name) ) {
            repository.delete(name);

            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.notFound().build();
    }

}
