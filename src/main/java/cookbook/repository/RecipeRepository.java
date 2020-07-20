package cookbook.repository;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
//    List<Recipe> findByNameOrAuthor(String name, String author);
//
//    List<Recipe> findByNameAndAuthor(String name, String author);
//
//    List<Recipe> findByNameOrAuthorOrIngredients(String name, String author, List<Ingredient> ingredients);
//
//    List<Recipe> findByNameAndAuthorAndIngredients(String name, String author, List<Ingredient> ingredients);
//
//    List<Recipe> findByIngredients(List<Ingredient> ingredients);
}
