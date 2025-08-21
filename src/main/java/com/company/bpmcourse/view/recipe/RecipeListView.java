package com.company.bpmcourse.view.recipe;

import com.company.bpmcourse.entity.Recipe;
import com.company.bpmcourse.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "recipes", layout = MainView.class)
@ViewController(id = "Recipe.list")
@ViewDescriptor(path = "recipe-list-view.xml")
@LookupComponent("recipesDataGrid")
@DialogMode(width = "64em")
public class RecipeListView extends StandardListView<Recipe> {
}