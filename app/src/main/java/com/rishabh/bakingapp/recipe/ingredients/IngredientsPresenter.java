package com.rishabh.bakingapp.recipe.ingredients;

import com.rishabh.bakingapp.base.BasePresenter;
import com.rishabh.bakingapp.recipe.stepdescription.StepsDescriptionView;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class IngredientsPresenter extends BasePresenter<IngredientsView> {


    public IngredientsPresenter(IngredientsView view) {
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
        if (getView() != null)
            getView().getIngredientsList();
    }

    public void ingredientsFetched() {
    if(getView()!=null)
        getView().setupAdapter();
    }
}