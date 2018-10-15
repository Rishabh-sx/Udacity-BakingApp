package com.rishabh.bakingapp.recipe.recipedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.base.BaseFragment;
import com.rishabh.bakingapp.pojo.Recipe.Ingredient;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipeDetailsFragment extends BaseFragment implements RecipeDetailView, RecipeDetailAdapter.RecyclerItemClickListener {

    private final String RECIPE = "recipe";
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private RecipeDetailHost mHost;
    private RecipeDetailPresenter presenter;
    private Recipe recipe;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeDetailHost) {
            mHost = (RecipeDetailHost) context;
        } else
            throw new IllegalStateException("host activity must implement RecipeDetailsFragmentHost");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new RecipeDetailPresenter(this);
        presenter.init();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public RecipeDetailsFragment getInstance(Recipe recipe) {
        Bundle bundle;
        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        if (recipe != null) {
            bundle = new Bundle();
            bundle.putParcelable(RECIPE, recipe);
            recipeDetailsFragment.setArguments(bundle);
        }
        return recipeDetailsFragment;
    }

    public interface RecipeDetailHost {
        void openIngredientView(List<Ingredient> ingredients);

        void openStepsView(int position);
    }


    @Override
    public void onItemClicked(int position) {
        if (position==0)
            mHost.openIngredientView(recipe.getIngredients());
        else
            mHost.openStepsView(position-1);
    }

    @Override
    public void getRecipe() {
        if (getArguments() != null && getArguments().containsKey(RECIPE) && getArguments().getParcelable(RECIPE) != null) {
            recipe = getArguments().getParcelable(RECIPE);
            presenter.recipeFetched();
        }
    }

    @Override
    public void setUpViews() {
        RecipeDetailAdapter recipesAdapter = new RecipeDetailAdapter(recipe, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(recipesAdapter);
    }
}