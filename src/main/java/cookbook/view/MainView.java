package cookbook.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import cookbook.controller.IngredientController;
import cookbook.controller.RecipeController;
import cookbook.model.Recipe;


@Route()
public class MainView extends VerticalLayout {
    private RecipeController recipeController;
    private IngredientController ingredientController;
    Grid<Recipe> recipeGrid;
    TextField recipeNameFilter;
    TextField recipeAuthorFilter;
    private Button searchBtn;


    public MainView(RecipeController recipeController, IngredientController ingredientController) {
        this.recipeController = recipeController;
        this.ingredientController = ingredientController;
        this.recipeGrid = new Grid<>(Recipe.class);
        this.recipeNameFilter = new TextField();
        this.recipeAuthorFilter = new TextField();
        this.searchBtn = new Button("Search recipes", VaadinIcon.SEARCH.create());
        HorizontalLayout actions = new HorizontalLayout(recipeNameFilter, recipeAuthorFilter, searchBtn);
        add(actions, recipeGrid);
        recipeGrid.setHeight("200px");
        recipeGrid.setColumns("id", "name", "author");
        recipeGrid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        recipeNameFilter.setPlaceholder("Filter by name");
        recipeAuthorFilter.setPlaceholder("Filter by author");
        ComponentEventListener<ClickEvent<Grid<Recipe>>> gridListener = (ComponentEventListener<ClickEvent<Grid<Recipe>>>) event -> {

        };
    }


}
