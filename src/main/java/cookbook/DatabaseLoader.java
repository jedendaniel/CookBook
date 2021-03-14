package cookbook;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import cookbook.repository.IngredientRepository;
import cookbook.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

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
        Ingredient water = new Ingredient("water");
        Ingredient egg = new Ingredient("egg");
        Ingredient flour = new Ingredient("flour");
        Ingredient salt = new Ingredient("salt");
        Ingredient milk = new Ingredient("milk");
//        ingredientRepository.saveAll(List.of(water, egg, flour, salt, milk));
//
//
//        Recipe scrambleEggs = new Recipe("scramble eggs", "Ddd");
//        recipeRepository.save(scrambleEggs);
//        scrambleEggs.addIngredient(egg, "2");
//        scrambleEggs.addIngredient(salt, "for taste");
////        recipeRepository.saveAll(List.of(scrambleEggs));
//
//        Recipe pancakes = new Recipe("pancakes", "Ddd");
//        recipeRepository.save(pancakes);
//        pancakes.addIngredients(Map.of(
//                water, "0.5 liter",
//                egg, "4",
//                flour, "1 kilo",
//                salt, "a bit"));
//        recipeRepository.save(pancakes);

        Map<Ingredient, String> milkPancakesIngredients = Map.of(
                milk, "0.5 liter",
                egg, "4",
                flour, "1 kilo",
                salt, "a bit");
//        ingredientRepository.save()
        Recipe milkPancakes = new Recipe("milkPancakes", "milkDdd", milkPancakesIngredients);
        recipeRepository.save(milkPancakes);
//        milkPancakes.addIngredients(milkPancakesIngredients);
//        recipeRepository.save(milkPancakes);

        Iterable<Recipe> all = recipeRepository.findAll();
        Optional<Ingredient> water1 = ingredientRepository.findById("water");
        System.out.println("a");
    }
}
