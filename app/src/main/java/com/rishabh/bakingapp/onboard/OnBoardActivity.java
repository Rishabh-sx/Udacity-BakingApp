package com.rishabh.bakingapp.onboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rishabh.bakingapp.base.BaseActivity;
import com.rishabh.bakingapp.onboard.recipelist.RecipeListFragment;
import com.rishabh.bakingapp.onboard.splash.SplashFragment;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;
import com.rishabh.bakingapp.recipe.RecipeActivity;
import com.rishabh.bakingapp.recipe.recipedetail.RecipeDetailsFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.rishabh.bakingapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class OnBoardActivity extends BaseActivity implements OnBoardView, RecipeListFragment.RecipeListHost, SplashFragment.ISplashHost {


    private static final String TAG = "OnBoardActivity";
    @BindView(R.id.container)
    FrameLayout container;
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.tv_back)
//    ImageView tvBack;
//    @BindView(R.id.rl_toolbar)
//    RelativeLayout rlToolbar;

    private OnBoardPresenter presenter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_onboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new OnBoardPresenter(this);
        presenter.init();
    }


    @Override
    public void initViews() {
//        rlToolbar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    /**
     * Method to generate string from .json file present in raw resource directory.
     *
     * @param inputStream is generated from the .json file present in raw resource directory.
     * @return
     */
    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showRecipeList() {
        addFragment(R.id.container, new RecipeListFragment().getInstance(), RecipeDetailsFragment.class.getName());
    }

    @Override
    public void loadRecipe(Recipe recipe) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);

    }


    @Override
    public void showSplash() {
        addFragment(R.id.container, new SplashFragment().getInstance(), SplashFragment.class.getName());
    }

    @Override
    public void steerToHomeFromSplash() {
        presenter.splashTimeOut();
    }

    @Override
    public void setupRecipeView() {
//        rlToolbar.setVisibility(View.VISIBLE);
//        tvBack.setVisibility(View.GONE);
//        tvTitle.setText("Bakers");
//        tvTitle.setVisibility(View.VISIBLE);
    }
}
