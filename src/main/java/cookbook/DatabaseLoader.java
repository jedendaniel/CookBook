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
    public void run(String... args) {
        Ingredient water = new Ingredient("Water");
        Ingredient egg = new Ingredient("Egg");
        Ingredient flour = new Ingredient("Flour");
        Ingredient salt = new Ingredient("Salt");
        ingredientRepository.saveAll(List.of(water, egg, flour, salt));


        Recipe scrambleEggs = new Recipe("Scramble eggs", "Ddd");
        recipeRepository.save(scrambleEggs);
        scrambleEggs.addIngredient(egg, "2");
        scrambleEggs.addIngredient(salt, "for taste");
        recipeRepository.saveAll(List.of(scrambleEggs));

        Recipe pancakes = new Recipe("Pancakes", "Ddd");
        recipeRepository.save(pancakes);
        pancakes.addIngredients(Map.of(
                water, "0.5 liter",
                egg, "4",
                flour, "1 kilo",
                salt, "a bit"));
        recipeRepository.save(pancakes);

        Iterable<Recipe> all = recipeRepository.findAll();
        System.out.println("a");
    }
}
