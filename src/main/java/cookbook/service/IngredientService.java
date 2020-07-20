package cookbook.service;

import cookbook.model.Ingredient;
import cookbook.repository.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Iterable<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }
}
