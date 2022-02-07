package cookbook.repository;

import cookbook.CookBookMain;
import cookbook.model.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import setup.TestDatabaseLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {CookBookMain.class, TestDatabaseLoader.class})
class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    void shouldFindIngredientByName() {
        Optional<Ingredient> i1 = ingredientRepository.findByName("i1");
        assertTrue(i1.isPresent());
        assertEquals("i1", i1.get().getName());
    }

    @Test
    void shouldSaveIngredient() {
        String newIngredient = "newIngredient";
        ingredientRepository.save(new Ingredient(newIngredient));
        Optional<Ingredient> ingredient = ingredientRepository.findByName(newIngredient);
        assertTrue(ingredient.isPresent());
        assertEquals(newIngredient, ingredient.get().getName());
    }
}