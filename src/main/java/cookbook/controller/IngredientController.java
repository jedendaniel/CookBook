package cookbook.controller;

import cookbook.model.Ingredient;
import cookbook.service.IngredientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(("/ingredients"))
public class IngredientController {
    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Ingredient> getAll() {
        return service.getAll();
    }

    @GetMapping("/{name}")
    public Optional<Ingredient> getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return service.create(ingredient);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient) {
        return service.update(ingredient);
    }

}
