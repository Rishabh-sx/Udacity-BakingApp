package com.rishabh.bakingapp.recipe.recipedetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecipeViewHolder> {

    private Recipe mRecipe;
    private RecyclerItemClickListener mListener;

    public RecipeDetailAdapter(Recipe recipe, RecyclerItemClickListener mListener) {
        mRecipe = recipe;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recipes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        if(position==0)
            holder.tvRecipe.setText("Ingredients");
        else{
            holder.tvRecipe.setText(String.format("Step %d\n%s", position, mRecipe.getSteps().get(position - 1).getShortDescription()));
        }
    }


    @Override
    public int getItemCount() {
        //Steps size + ingredients size;
        return mRecipe.getSteps().size()+1;

    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recipe)
        TextView tvRecipe;

        RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListener {

        void onItemClicked(int position);

    }

}
