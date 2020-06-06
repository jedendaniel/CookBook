package cookbook.controller;

import cookbook.service.RecipeService;
import org.springframework.stereotype.Controller;

@Controller("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
