package com.rishabh.bakingapp.onboard;

import com.rishabh.bakingapp.base.BaseView;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import java.util.List;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public interface OnBoardView extends BaseView {


    void showRecipeList();

    void initViews();

    void showSplash();

    void setupRecipeView();
}
