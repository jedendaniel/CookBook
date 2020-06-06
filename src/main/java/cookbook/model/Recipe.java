package cookbook.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Map;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private String name;
    private String author;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_ingredient")
    @ElementCollection
//    @CollectionTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"))
//    @MapKeyJoinColumn(name = "ingredient_id")
    private Map<Ingredient, String> ingredients;

    public Recipe(String name, String author, Map<Ingredient, String> ingredients) {
        this.name = name;
        this.author = author;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
