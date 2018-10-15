package com.rishabh.bakingapp.onboard.recipelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> mList;
    private RecyclerItemClickListener mListener;

    public RecipesAdapter(ArrayList<Recipe> recipeArrayList, RecyclerItemClickListener mListener) {
        this.mList = recipeArrayList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recipes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.tvRecipe.setText(mList.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return mList.size();
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
                    mListener.onItemClicked(mList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface RecyclerItemClickListener {


        void onItemClicked(Recipe recipe);
    }

    public ArrayList<Recipe> getmList() {
        return mList;
    }
}
