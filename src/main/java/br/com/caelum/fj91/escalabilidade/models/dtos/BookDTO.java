package br.com.caelum.fj91.escalabilidade.models.dtos;

import br.com.caelum.fj91.escalabilidade.models.Author;
import br.com.caelum.fj91.escalabilidade.models.Book;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Arrays;

public class BookDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String summary;

    @NotEmpty
    private String[] authors;

    public Book toEntity() {
        return new Book(title, summary, Arrays.stream(this.authors).map(Author::new).toArray(Author[]::new));
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String[] getAuthors() {
        return authors;
    }
}
