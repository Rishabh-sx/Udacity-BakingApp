package com.rishabh.bakingapp.onboard.recipelist;

import com.rishabh.bakingapp.base.BaseModel;
import com.rishabh.bakingapp.network.NetworkResponse;
import com.rishabh.bakingapp.pojo.FailureResponse;
import com.rishabh.bakingapp.pojo.Recipe.RecipeResponse;

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
        getDataManager().getRecipeData().enqueue(new NetworkResponse<RecipeResponse>(this) {
            @Override
            public void onSuccess(RecipeResponse body) {
                if(getListener()!=null && body.getCODE()==200)
                    getListener().onRecipeResponse(body.getRecipeList());
                else if(getListener()!=null) getListener().onRecipeResponse(null);
            }

            @Override
            public void onFailure(int code, FailureResponse failureResponse) {
                if(getListener()!=null) getListener().onRecipeResponse(null);
            }

            @Override
            public void onError(Throwable t) {
                if(getListener()!=null) getListener().onRecipeResponse(null);
            }
        });
    }

}
