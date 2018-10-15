package com.rishabh.bakingapp.recipe;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.base.BaseActivity;
import com.rishabh.bakingapp.onboard.recipelist.RecipeListFragment;
import com.rishabh.bakingapp.pojo.Recipe.Ingredient;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;
import com.rishabh.bakingapp.pojo.Recipe.Step;
import com.rishabh.bakingapp.recipe.ingredients.IngredientsFragment;
import com.rishabh.bakingapp.recipe.recipedetail.RecipeDetailsFragment;
import com.rishabh.bakingapp.recipe.stepdescription.StepsDescriptionFragment;
import com.rishabh.bakingapp.widget.BakingWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipeActivity extends BaseActivity implements RecipeView, RecipeListFragment.RecipeListHost, RecipeDetailsFragment.RecipeDetailHost {


    private static final String TAG = "OnBoardActivity";
    @BindView(R.id.fl_master)
    FrameLayout container;
    @BindView(R.id.tv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private RecipePresenter presenter;
    private ArrayList<Recipe> recipeArrayList;
    private Recipe recipe;
    private boolean twoPane;
    private StepsDescriptionFragment stepsDescriptionFragment;
    private boolean isScreenRotated;

    @Override
    protected int getResourceId() {
        return R.layout.activity_recipe;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (savedInstanceState != null && savedInstanceState.getBoolean("rotated")) {
            isScreenRotated = true;
        }
        presenter = new RecipePresenter(this);
        presenter.init();

    }

    @Override
    public void loadFromSavedInstance(int position) {

    }

    @Override
    public void initViews() {
        tvTitle.setText(recipe.getName());
    }


    @Override
    public void loadRecipe(Recipe recipe) {

    }

    @Override
    public void openStepsView(int position) {
        if (twoPane) {
            if (getSupportFragmentManager().findFragmentById(R.id.fl_detail) != null
                    && getSupportFragmentManager().findFragmentById(R.id.fl_detail)
                    instanceof StepsDescriptionFragment
                    && stepsDescriptionFragment == null) {
                stepsDescriptionFragment = (StepsDescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.fl_detail);
                stepsDescriptionFragment.loadRecipe(position);
            } else if (stepsDescriptionFragment == null) {
                stepsDescriptionFragment = new StepsDescriptionFragment().getInstance((ArrayList<Step>) recipe.getSteps(), position);
                replaceFragment(R.id.fl_detail, stepsDescriptionFragment, StepsDescriptionFragment.class.getName());
            } else {
                stepsDescriptionFragment.loadRecipe(position);
            }

        } else {
            addFragmentWithBackstack(R.id.fl_master, new StepsDescriptionFragment().getInstance((ArrayList<Step>) recipe.getSteps(), position), StepsDescriptionFragment.class.getName());
        }

    }

    @Override
    public void openIngredientView(List<Ingredient> ingredients) {
        if (twoPane) {
            replaceFragment(R.id.fl_detail, new IngredientsFragment().getInstance((ArrayList<Ingredient>) ingredients), IngredientsFragment.class.getName());
        } else {
            addFragmentWithBackstack(R.id.fl_master, new IngredientsFragment().getInstance((ArrayList<Ingredient>) ingredients), IngredientsFragment.class.getName());
        }
    }

    @Override
    public void getRecipe() {
        if (getIntent() != null && getIntent().hasExtra("recipe")
                && getIntent().getParcelableExtra("recipe") != null) {
            recipe = getIntent().getParcelableExtra("recipe");
            presenter.recipeFetched(recipe);
        }
    }

    @Override
    public void showRecipeListing() {
        addFragment(R.id.fl_master, new RecipeDetailsFragment().getInstance(recipe), RecipeDetailsFragment.class.getName());
    }

    @Override
    public void determinePaneLayout() {
        FrameLayout frameLayout = findViewById(R.id.fl_detail);
        if (frameLayout != null) {
            twoPane = true;
        }
        if (!isScreenRotated)
            presenter.layoutDetermined(twoPane);
    }

    @Override
    public void loadInitialStep() {

        openIngredientView(recipe.getIngredients());
    }


    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        Log.e(TAG, "onDestroy: ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("rotated", true);
    }


    @Override
    public void updateWidget() {
        Intent intent = new Intent(this, BakingWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager.getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(), BakingWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
//getAppWidgetIds
    }
}
