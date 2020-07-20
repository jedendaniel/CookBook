package cookbook.controller;

import cookbook.model.Recipe;
import cookbook.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @PostMapping
    Recipe create(Recipe recipe) {
        return service.createRecipe(recipe);
    }

    @GetMapping
    Iterable<Recipe> getAll() {
        return service.getRecipes();
    }
}
