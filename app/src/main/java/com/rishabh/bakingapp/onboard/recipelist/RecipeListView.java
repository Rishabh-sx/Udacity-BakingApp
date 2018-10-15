package com.rishabh.bakingapp.onboard.recipelist;

import com.rishabh.bakingapp.base.BaseView;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import java.util.ArrayList;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public interface RecipeListView extends BaseView {

    void setupRecipeAdapter(ArrayList<Recipe> recipeArrayList);

    void updateTitle();
    //void getRecipes();
}
