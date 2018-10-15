package com.rishabh.bakingapp.recipe.ingredients;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.pojo.Recipe.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.RecipeViewHolder> {

    private ArrayList<Ingredient> mIngredientArrayList;


    public IngredientsAdapter(ArrayList<Ingredient> ingredientArrayList) {
        mIngredientArrayList = ingredientArrayList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        holder.tvIngredient.setText(mIngredientArrayList.get(position).getIngredient());
        holder.tvMeasure.setText(
                  String.format("%s-%s",
                  mIngredientArrayList.get(position).getQuantity(),
                  mIngredientArrayList.get(position).getMeasure()));

    }


    @Override
    public int getItemCount() {
        //Steps size + ingredients size;
        return mIngredientArrayList.size();

    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ingredient)
        TextView tvIngredient;
        @BindView(R.id.tv_measure)
        TextView tvMeasure;

        RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
