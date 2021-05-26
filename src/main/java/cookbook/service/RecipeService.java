package cookbook.service;

import cookbook.model.Recipe;
import cookbook.model.RecipeSearchData;
import cookbook.repository.RecipeRepository;
import org.springframework.stereotype.Service;

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
        return recipeRepository.findByNameAuthorAndIngredients(recipeSearchData.getName(), recipeSearchData.getAuthor(), requiredIngredients, requiredIngredients.size());
    }
}
