package cookbook.repository;

import cookbook.model.Recipe;
import cookbook.model.RecipeIngredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long>, QueryByExampleExecutor<Recipe> {
//    List<Recipe> findByNameOrAuthor(String name, String author);
//
//    List<Recipe> findByNameAndAuthor(String name, String author);
//
//    List<Recipe> findByNameOrAuthorOrIngredients(String name, String author, List<Ingredient> ingredients);
//
@Query("select r from Recipe r where r.name = :name and ingredients in :ingredients")
List<Recipe> findBySearchData(String name, List<RecipeIngredient> ingredients);

    List<Recipe> findByNameAndAuthorAndIngredientsIn(String name, String author, RecipeIngredient... ingredients);

//
//    List<Recipe> findByIngredients(List<Ingredient> ingredients);
}
