package cookbook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Entity
@Setter
@Getter
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id = new RecipeIngredientId();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("recipeId")
    @JsonBackReference
    private Recipe recipe;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ingredientId")
    @JsonBackReference
    private Ingredient ingredient;
    private String amount;

    public RecipeIngredient() {
    }

    RecipeIngredient(Recipe recipe, Ingredient ingredient, String amount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.id = new RecipeIngredientId(recipe.getId(), ingredient.getName());
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient);
    }
}