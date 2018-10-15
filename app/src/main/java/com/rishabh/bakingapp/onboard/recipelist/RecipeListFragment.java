package com.rishabh.bakingapp.onboard.recipelist;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.base.BaseFragment;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipeListFragment extends BaseFragment implements RecipeListView, RecipesAdapter.RecyclerItemClickListener {


    private static final String RECIPE_LIST = "recipe_list";
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.tv_back)
    ImageView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_toolbar)
    RelativeLayout rlToolbar;
    private RecipeListHost mHost;
    private RecipeListPresenter presenter;
    private RecipesAdapter recipesAdapter;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeListHost) {
            mHost = (RecipeListHost) context;
        } else
            throw new IllegalStateException("host activity must implement FeedsFragmentHost");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new RecipeListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            presenter.init();
        } else {
            presenter.onViewRecreated();
            if (savedInstanceState.containsKey("list")
                    && savedInstanceState.getParcelableArrayList("list") != null) {
                setupRecipeAdapter(savedInstanceState.<Recipe>getParcelableArrayList("list"));
            } else {
                presenter.init();
            }
        }


    }

    @Override
    public void updateTitle() {
        tvTitle.setText(getString(R.string.txt_baker));
        tvBack.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public BaseFragment getInstance() {
        return new RecipeListFragment();
    }

    public interface RecipeListHost {
        void loadRecipe(Recipe recipe);
    }


    @Override
    public void setupRecipeAdapter(ArrayList<Recipe> recipeArrayList) {
        recipesAdapter = new RecipesAdapter(recipeArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(recipesAdapter);
    }

    @Override
    public void onItemClicked(Recipe recipe) {
        mHost.loadRecipe(recipe);
    }

    /*@Override
    public void getRecipes() {
        if(getArguments()!=null && getArguments().containsKey(RECIPE_LIST) && getArguments().getParcelableArrayList(RECIPE_LIST)!=null) {
            ArrayList<Recipe> recipeArrayList = getArguments().getParcelableArrayList(RECIPE_LIST);
            if (recipeArrayList != null)
                presenter.recipeListFetched(recipeArrayList);
        }
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (recipesAdapter != null) {
            if (recipesAdapter.getmList() != null)
                outState.putParcelableArrayList("list", recipesAdapter.getmList());
        }
    }
}
