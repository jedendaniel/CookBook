package cookbook.service;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import cookbook.model.RecipeIngredient;
import cookbook.model.RecipeSearchData;
import cookbook.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> getById(Long id) {
        return recipeRepository.findById(id);
    }

    public Iterable<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public List<Recipe> findByNameAuthorAndIngredients(RecipeSearchData recipeSearchData) {
        List<String> requiredIngredients = recipeSearchData.getRequiredIngredients();
        List<Recipe> recipes = recipeRepository.findByNameAuthorAndIngredients(
                recipeSearchData.getName(), recipeSearchData.getAuthor(), requiredIngredients, requiredIngredients.size());
        LinkedList<Recipe> sortedRecipes = new LinkedList<>(recipes);
        sortedRecipes.sort((r1, r2) -> (int) (count(recipeSearchData.getOptionalIngredients(), r2) - count(recipeSearchData.getOptionalIngredients(), r1)));
        return sortedRecipes;
    }

    private long count(List<String> optionalIngredients, Recipe recipe) {
        return recipe.getIngredients().stream()
                .map(RecipeIngredient::getIngredient)
                .map(Ingredient::getName)
                .filter(optionalIngredients::contains)
                .count();
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
