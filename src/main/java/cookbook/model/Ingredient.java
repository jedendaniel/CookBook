package cookbook.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Ingredient implements Serializable {

    @Id
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "ingredient"
    )
    @JsonManagedReference
    private List<RecipeIngredient> recipes = new ArrayList<>();

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
