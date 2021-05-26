import cookbook.CookBookMain;
import cookbook.controller.IngredientController;
import cookbook.controller.RecipeController;
import cookbook.repository.IngredientRepository;
import cookbook.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import setup.TestDatabaseLoader;

@SpringBootTest(classes = {CookBookMain.class, TestDatabaseLoader.class})
class CookBookIT {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private IngredientController ingredientController;
    @Autowired
    private RecipeController recipeController;

    @Test
    void dummyTest() {
    }
}
