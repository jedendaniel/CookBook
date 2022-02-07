package cookbook.service;

import cookbook.model.Ingredient;
import cookbook.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        ingredientRepository.findByName(ingredient.getName());
        return ingredientRepository.save(ingredient);
    }

    public Optional<Ingredient> getByName(String string) {
        return ingredientRepository.findById(string);
    }

    public Iterable<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }
}
