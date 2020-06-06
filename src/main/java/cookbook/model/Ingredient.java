package cookbook.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Map;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "recipe_ingredient")
    @ElementCollection
//    @CollectionTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "ingredient_id"))
//    @MapKeyJoinColumn(name = "recipe_id")
    private Map<Recipe, String> recipes;

    public Ingredient(String name, Map<Recipe, String> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recipes=" + recipes +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ingredient && ((Ingredient) obj).getId().equals(this.id);
    }
}
