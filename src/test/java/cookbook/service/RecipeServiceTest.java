package cookbook.service;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import cookbook.model.RecipeSearchData;
import cookbook.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class RecipeServiceTest {

    private final RecipeRepository recipeRepository = mock(RecipeRepository.class);
    private final RecipeService recipeService = new RecipeService(recipeRepository);

    @Test
    void shouldReturnRecipesSortedByCountOfOptionalIngredients() {
        Ingredient i1 = new Ingredient("i1");
        Ingredient i2 = new Ingredient("i2");
        Ingredient i3 = new Ingredient("i3");
        List<Recipe> recipes = List.of(
                new Recipe("n0", "a", Map.of()),
                new Recipe("n1", "a", Map.of(i1, "1")),
                new Recipe("n2", "a", Map.of(i1, "1", i2, "2")),
                new Recipe("n3", "a", Map.of(i1, "1", i2, "2", i3, "3"))
        );
        when(recipeRepository.findByNameAuthorAndIngredients(anyString(), anyString(), anyList(), anyLong())).thenReturn(recipes);

        RecipeSearchData data = RecipeSearchData
                .getBuilder()
                .setName("n")
                .setAuthor("a")
                .setRequiredIngredients(List.of())
                .setOptionalIngredients(List.of(i1.getName(), i2.getName(), i3.getName())).build();
        List<Recipe> foundRecipes = recipeService.findByNameAuthorAndIngredients(data);

        assertEquals(recipes.size(), foundRecipes.size());
        assertEquals("n3", foundRecipes.get(0).getName());
        assertEquals("n2", foundRecipes.get(1).getName());
        assertEquals("n1", foundRecipes.get(2).getName());
        assertEquals("n0", foundRecipes.get(3).getName());
    }
}