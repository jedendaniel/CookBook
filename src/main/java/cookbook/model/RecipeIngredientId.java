package cookbook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class RecipeIngredientId implements Serializable {

    @Column(name = "recipe_Id")
    private Long recipeId;
    @Column(name = "ingredient_Id")
    private String ingredientId;

    RecipeIngredientId() {
    }

    RecipeIngredientId(Long recipeId, String ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientId that = (RecipeIngredientId) o;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }
}
