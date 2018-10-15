package com.rishabh.bakingapp.recipe;

import com.rishabh.bakingapp.base.BasePresenter;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipePresenter extends BasePresenter<RecipeView> implements RecipeModelListener {


    private RecipeModel model;

    public RecipePresenter(RecipeView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new RecipeModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {
    }

    public void init() {
        if (getView() != null) {
            getView().getRecipe();
        }
    }

    public void recipeFetched(Recipe recipe) {
        if (getView() != null) {
            model.saveRecipeDataForWidget(recipe);
            getView().initViews();
            getView().determinePaneLayout();
        }
    }

    public void layoutDetermined(boolean twoPane) {
        if (getView() != null) {
            if (twoPane) {
                getView().showRecipeListing();
                getView().loadInitialStep();
            } else {
                getView().showRecipeListing();

            }
        }
    }

    public void loadFromSavedInstance(int position){
        if(getView()!=null){
            getView().loadFromSavedInstance(position);
        }
    }

    @Override
    public void updateWidget() {
        if(getView()!=null)
            getView().updateWidget();
    }
}
