package cookbook.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String author;
    @OneToMany(
            mappedBy = "recipe",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Recipe(String name, String author, List<RecipeIngredient> ingredients) {
        this.name = name;
        this.author = author;
        this.ingredients = ingredients;
    }

    public Recipe(String name, String author, Map<Ingredient, String> ingredients) {
        this.name = name;
        this.author = author;
        addIngredients(ingredients);
    }

    public void addIngredients(Map<Ingredient, String> ingredients) {
        ingredients.forEach(this::addIngredient);
    }

    public void addIngredient(Ingredient ingredient, String amount) {
        RecipeIngredient recipeIngredient = new RecipeIngredient(this, ingredient, amount);
        ingredients.add(recipeIngredient);
        ingredient.getRecipes().add(recipeIngredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        for (Iterator<RecipeIngredient> iterator = ingredients.iterator();
             iterator.hasNext(); ) {
            RecipeIngredient recipeIngredient = iterator.next();

            if (recipeIngredient.getRecipe().equals(this) &&
                    recipeIngredient.getIngredient().equals(ingredient)) {
                iterator.remove();
                recipeIngredient.getIngredient().getRecipes().remove(recipeIngredient);
                recipeIngredient.setIngredient(null);
                recipeIngredient.setRecipe(null);
                recipeIngredient.setAmount(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(author, recipe.author) &&
                Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }
}
