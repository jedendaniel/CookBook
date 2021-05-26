package cookbook.repository;

import cookbook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByName(String name);

    @Query("select r from Recipe r join RecipeIngredient ri on r.id = ri.recipe.id where " +
            "(:name is null or r.name = :name) " +
            "and (:author is null or r.author = :author) " +
            "and (:size = 0L or ri.ingredient.name in :ingredients) " +
            "group by r.id having (count(r.id) >= :size)")
    List<Recipe> findByNameAuthorAndIngredients(String name, String author, List<String> ingredients, long size);
}
