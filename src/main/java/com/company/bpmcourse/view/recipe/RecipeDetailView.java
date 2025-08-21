package com.company.bpmcourse.view.recipe;

import com.company.bpmcourse.entity.Recipe;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "recipes/:id", layout = MainView.class)
@ViewController(id = "Recipe.detail")
@ViewDescriptor(path = "recipe-detail-view.xml")
@EditedEntityContainer("recipeDc")
public class RecipeDetailView extends StandardDetailView<Recipe> {
}