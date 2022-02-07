package cookbook.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("newRecipeForm")
public class NewRecipeForm extends VerticalLayout {
    private final TextField name = new TextField("Recipe name");
    private final List<TextField> ingredientTextFields = new ArrayList<>();
    private final VerticalLayout ingredientsLayout = new VerticalLayout();
    private final Button saveButton = new Button("Save");

    public NewRecipeForm() {
        addClassName("new-recipe-form");
        Label tittle = new Label("Create new recipe");
        tittle.setHeight("20px");
        VerticalLayout layout = new VerticalLayout(tittle, name, new Label("Ingredients"), ingredientsLayout, saveButton);
        anotherIngredient();
        add(layout);
    }

    private void anotherIngredient() {
        TextField anotherTextField = new TextField();
        anotherTextField.setMinWidth("50px");
        ingredientTextFields.add(anotherTextField);
        Button anotherButton = new Button();
        anotherButton.setIcon(VaadinIcon.PLUS.create());
        anotherButton.addClickListener(event -> anotherIngredient());
        HorizontalLayout anotherLayout = new HorizontalLayout(anotherTextField, anotherButton);
        ingredientsLayout.addAndExpand(anotherLayout);
    }

}
