package escalabilidade.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String name;

    /**
     * @deprecated
     */
    @Deprecated
    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
