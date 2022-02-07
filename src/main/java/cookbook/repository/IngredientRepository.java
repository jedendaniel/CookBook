package cookbook.repository;

import cookbook.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    Optional<Ingredient> findByName(String name);
}
