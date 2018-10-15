package com.rishabh.bakingapp.onboard;

import android.util.Log;

import com.rishabh.bakingapp.base.BaseModel;
import com.rishabh.bakingapp.network.NetworkResponse;
import com.rishabh.bakingapp.pojo.FailureResponse;
import com.rishabh.bakingapp.pojo.Recipe.RecipeResponse;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

class OnBoardModel extends BaseModel<OnBoardModelListener> {

    private static final String TAG = "OnBoardModel";

    public OnBoardModel(OnBoardModelListener listener) {
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
