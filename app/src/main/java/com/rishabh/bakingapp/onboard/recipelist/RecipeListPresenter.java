package com.rishabh.bakingapp.onboard.recipelist;

import com.rishabh.bakingapp.base.BasePresenter;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipeListPresenter extends BasePresenter<RecipeListView> implements RecipeModelListener {


    private RecipeModel mModel;

    public RecipeListPresenter(RecipeListView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        mModel = new RecipeModel(this);
    }

    @Override
    protected void destroy() {
    }

    @Override
    protected void initView() {
    }


    public void init() {
        if (getView() != null) {
            getView().showProgressDialog();
            mModel.init();
            getView().updateTitle();

        }
    }

    @Override
    public void onRecipeResponse(List<Recipe> recipeList) {
        if (getView() != null) {
            getView().hideProgressDialog();
            if (recipeList != null) {
                getView().setupRecipeAdapter((ArrayList<Recipe>) recipeList);
            }
        }
    }

    public void onViewRecreated() {
        if (getView() != null) {
            getView().updateTitle();
        }
    }
}