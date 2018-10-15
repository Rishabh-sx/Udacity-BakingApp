package com.rishabh.bakingapp.recipe.ingredients;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.base.BaseFragment;
import com.rishabh.bakingapp.pojo.Recipe.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class IngredientsFragment extends BaseFragment implements IngredientsView {


    private static final String INGREDIENTS = "ingredients";
    @BindView(R.id.rv)
    RecyclerView rv;
    private Unbinder unbinder;
    private IngredientsPresenter presenter;
    private ArrayList<Ingredient> ingredientsArrayList;

    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new IngredientsPresenter(this);
        presenter.init();
        setRetainInstance(true);
    }


    public IngredientsFragment getInstance(ArrayList<Ingredient> ingredientArrayList) {
        Bundle bundle;
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        if (ingredientArrayList != null) {
            bundle = new Bundle();
            bundle.putParcelableArrayList(INGREDIENTS, ingredientArrayList);
            ingredientsFragment.setArguments(bundle);
        }
        return ingredientsFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getIngredientsList() {
        if (getArguments() != null
                && getArguments().containsKey(INGREDIENTS)
                && getArguments().getParcelableArrayList(INGREDIENTS) != null) {

            ingredientsArrayList = getArguments().getParcelableArrayList(INGREDIENTS);

            if (ingredientsArrayList != null)
                presenter.ingredientsFetched();

        }
    }

    @Override
    public void setupAdapter() {
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredientsArrayList);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, 1);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.setAdapter(ingredientsAdapter);
    }
}



