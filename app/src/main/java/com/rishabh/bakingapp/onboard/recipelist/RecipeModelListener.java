package com.rishabh.bakingapp.onboard.recipelist;

import com.rishabh.bakingapp.base.BaseModelListener;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import java.util.List;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

interface RecipeModelListener extends BaseModelListener {


    void onRecipeResponse(List<Recipe> o);
}
