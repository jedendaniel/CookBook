package cookbook.controller;

import cookbook.model.Ingredient;
import cookbook.service.IngredientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(("/ingredients"))
public class IngredientController {
    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Ingredient> getAll() {
        return service.getIngredients();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return service.createIngredient(ingredient);
    }
}
