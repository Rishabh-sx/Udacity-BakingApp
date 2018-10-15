package com.rishabh.bakingapp.recipe.ingredients;

import com.rishabh.bakingapp.base.BaseView;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public interface IngredientsView extends BaseView {


    void getIngredientsList();

    void setupAdapter();
}
