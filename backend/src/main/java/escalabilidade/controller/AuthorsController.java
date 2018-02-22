package escalabilidade.controller;

import escalabilidade.models.Author;
import escalabilidade.repositories.AuthorsRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authors")
public class AuthorsController {


    private final AuthorsRepository repository;

    public AuthorsController(AuthorsRepository repository) {
        this.repository = repository;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Author> getAll(){
        return repository.findAll();
    }


    @GetMapping(value = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> getById(@PathVariable String name){
        return repository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
