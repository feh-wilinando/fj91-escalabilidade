package escalabilidade.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String summary;

    @ManyToMany
    @NotEmpty
    private List<Author> authors = new ArrayList<>();


    /**
     * @deprecated (frameworks only)
     */
    @Deprecated
    private Book() {}

    public Book(String title, String summary, Author... authors) {
        this.title = title;
        this.summary = summary;
        this.authors = Arrays.asList(authors);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
