package com.rishabh.bakingapp.recipe.stepdescription;

import android.os.Bundle;

import com.rishabh.bakingapp.base.BasePresenter;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class StepsDescriptionPresenter extends BasePresenter<StepsDescriptionView> {


    public StepsDescriptionPresenter(StepsDescriptionView view) {
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

    public void init( ) {
        if (getView() != null)
            getView().getStep();
    }

    public void stepFetched(int position) {
        if (getView() != null)
            getView().setUpViews(position);
    }

    public void loadStep(int position) {
        if (getView() != null)
            getView().setUpViews(position);
    }
}