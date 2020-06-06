package cookbook.service;

import cookbook.model.Recipe;
import cookbook.model.RecipeSearchData;
import cookbook.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Set<Recipe> getRecipes(RecipeSearchData recipe) {
        recipeRepository.findByNameAndAuthorAndIngredients(recipe.getName(), recipe.getAuthor(), recipe.getObligatoryIngredients());
        return Set.of();
    }

    private boolean isAnyFieldMissing(RecipeSearchData recipe) {
        return Objects.isNull(recipe.getName()) || Objects.isNull(recipe.getAuthor()) || recipe.getObligatoryIngredients().isEmpty();
    }
}
