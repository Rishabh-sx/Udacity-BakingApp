package com.rishabh.bakingapp.recipe.recipedetail;

import com.rishabh.bakingapp.base.BasePresenter;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipeDetailPresenter extends BasePresenter<RecipeDetailView> {


    public RecipeDetailPresenter(RecipeDetailView view) {
        super(view);
    }

    @Override
    protected void setModel() {
    }

    @Override
    protected void destroy() {
    }

    @Override
    protected void initView() {
    }


    public void init() {
        if(getView()!=null)
            getView().getRecipe();
    }

    public void recipeFetched() {
        if(getView()!=null)
        {
            getView().setUpViews();
        }
    }
}