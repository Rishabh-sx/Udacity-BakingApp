package com.rishabh.bakingapp.recipe;

import com.rishabh.bakingapp.base.BaseModel;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

class RecipeModel extends BaseModel<RecipeModelListener> {

    private static final String TAG = "OnBoardModel";

    public RecipeModel(RecipeModelListener listener) {
        super(listener);
    }

    @Override
    public void init() {

    }

    public void saveRecipeDataForWidget(Recipe recipe) {
        getDataManager().saveDataForWidget(recipe);
        if(getListener()!=null){
            getListener().updateWidget();
        }
    }
}
