package cookbook;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import cookbook.repository.IngredientRepository;
import cookbook.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public DatabaseLoader(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Ingredient water = new Ingredient("Water", Map.of());
        Ingredient egg = new Ingredient("Egg", Map.of());
        Ingredient flour = new Ingredient("Flour", Map.of());
        Ingredient salt = new Ingredient("Salt", Map.of());
        ingredientRepository.saveAll(List.of(water, egg, flour));

        Recipe scrambleEggs = new Recipe("Scramble eggs", "Ddd", Map.of(
                egg, "2",
                salt, "for taste"
        ));
        Recipe pancakes = new Recipe("Pancakes", "Ddd", Map.of(
                egg, "4",
                water, "1 liter",
                flour, "0,5 kg"
        ));
        recipeRepository.saveAll(List.of(scrambleEggs, pancakes));
    }
}
