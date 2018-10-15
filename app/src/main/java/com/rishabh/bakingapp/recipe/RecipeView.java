package com.rishabh.bakingapp.recipe;

import com.rishabh.bakingapp.base.BaseView;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public interface RecipeView extends BaseView {


    void determinePaneLayout();

    void getRecipe();

    void showRecipeListing();

    void loadInitialStep();

    void initViews();

    void loadFromSavedInstance(int position);

    void updateWidget();
}
