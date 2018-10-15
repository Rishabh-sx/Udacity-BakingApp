package com.rishabh.bakingapp.recipe.stepdescription;

import android.os.Bundle;

import com.rishabh.bakingapp.base.BaseView;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public interface StepsDescriptionView extends BaseView {

    void getStep();
    void setUpViews(int position);
}
