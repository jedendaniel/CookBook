package cookbook.controller;

import cookbook.model.Recipe;
import cookbook.model.RecipeSearchData;
import cookbook.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/all")
    public Iterable<Recipe> getAll() {
        return recipeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getById(@PathVariable Long id) {
        return recipeService.getById(id);
    }

    @GetMapping
    public List<Recipe> getRecipes(@RequestParam(required = false) String author,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) List<String> requiredIngredients,
                                   @RequestParam(required = false) List<String> optionalIngredients) {
        RecipeSearchData recipeSearchData = RecipeSearchData.getBuilder()
                .setName(name)
                .setAuthor(author)
                .setRequiredIngredients(requiredIngredients)
                .setOptionalIngredients(optionalIngredients)
                .build();
        return recipeService.findByNameAuthorAndIngredients(recipeSearchData);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }
}
