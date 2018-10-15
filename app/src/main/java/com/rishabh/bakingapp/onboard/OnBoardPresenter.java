package com.rishabh.bakingapp.onboard;

import com.rishabh.bakingapp.base.BasePresenter;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;
import com.rishabh.bakingapp.utils.AppUtils;

import java.util.List;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class OnBoardPresenter extends BasePresenter<OnBoardView> /*implements OnBoardModelListener*/ {


    //private OnBoardModel model;

    public OnBoardPresenter(OnBoardView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        //model = new OnBoardModel(this);
    }

    @Override
    protected void destroy() {
        //  model.detachListener();
        //  model = null;
    }

    @Override
    protected void initView() {
    }


    public void init() {
        if (getView() != null) {
            getView().initViews();
            getView().showSplash();
        }
    }

    public void datafetched() {
        if (getView() != null)
            getView().showRecipeList();
    }
/*
    @Override
    public void onRecipeResponse(List<Recipe> recipeList) {
        if (getView() != null) {
            getView().hideProgressDialog();
            if (recipeList != null) {
                getView().onRecipeListFetched(recipeList);
            }else getView().showErrorScreen();
        }
    }*/

    public void splashTimeOut() {
        //  getView().showProgressDialog();
        if (getView() != null) {
            getView().setupRecipeView();
            getView().showRecipeList();
        }
    }
}
