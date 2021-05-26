package cookbook.repository;

import cookbook.CookBookMain;
import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import cookbook.model.RecipeIngredient;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import setup.TestDatabaseLoader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = {CookBookMain.class, TestDatabaseLoader.class})
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    private static Stream<Arguments> provideParametersToGetRecipes() {
        return Stream.of(
                Arguments.of(null, null, null, 2),
                Arguments.of("a1", null, null, 2),
                Arguments.of("a1", "r1", null, 1),
                Arguments.of("a1", "r1", List.of("i1"), 1),
                Arguments.of(null, null, List.of("i1"), 2),
                Arguments.of(null, null, List.of("i1", "i2"), 1),
                Arguments.of(null, null, List.of("i2"), 1),
                Arguments.of("a0", null, null, 0),
                Arguments.of(null, null, List.of("i0"), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersToGetRecipes")
    void shouldReturnRecipesByRequiredParams(String author, String name, List<String> reqIng, int count) {
        long ingCount = reqIng != null ? reqIng.size() : 0L;
        List<Recipe> recipes = recipeRepository.findByNameAuthorAndIngredients(name, author, reqIng, ingCount);
        assertEquals(count, recipes.size());
        if (author != null) recipes.stream().map(Recipe::getAuthor).forEach(a -> assertEquals(author, a));
        if (name != null) recipes.stream().map(Recipe::getName).forEach(n -> assertEquals(name, n));
        if (reqIng != null) recipes.stream().map(Recipe::getIngredients).map(i -> i.stream()
                .map(RecipeIngredient::getIngredient).map(Ingredient::getName).collect(Collectors.toList()))
                .forEach(i -> assertTrue(i.containsAll(reqIng)));
    }
}