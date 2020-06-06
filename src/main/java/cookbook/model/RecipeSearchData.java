package cookbook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecipeSearchData {
    private String name;
    private String author;
    private List<Ingredient> obligatoryIngredients;
    private List<Ingredient> optionalIngredients;
}
