package setup;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import cookbook.repository.RecipeRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class TestDatabaseLoader {
    private final RecipeRepository recipeRepository;

    public TestDatabaseLoader(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @PostConstruct
    private void setupTestData() {
        System.out.println("----------------\ninitializing test database\n---------------");
        Ingredient i1 = new Ingredient("i1");
        Ingredient i2 = new Ingredient("i2");
        Ingredient i3 = new Ingredient("i3");
        Recipe r1 = new Recipe("r1", "a1", Map.of(i1, "1", i2, "2.2l"));
        Recipe r2 = new Recipe("r2", "a1", Map.of(i1, "1", i3, "three spoons"));
        recipeRepository.saveAll(List.of(r1, r2));
    }
}
