package com.rishabh.bakingapp.data;

import android.content.Context;
import android.preference.Preference;

import com.rishabh.bakingapp.data.api.ApiManager;
import com.rishabh.bakingapp.data.prefrence.PreferenceKeys;
import com.rishabh.bakingapp.data.prefrence.PreferenceManager;
import com.rishabh.bakingapp.pojo.Recipe.Recipe;
import com.rishabh.bakingapp.pojo.Recipe.RecipeResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class DataManager implements IDataManager {

    private ApiManager apiManager;
    private PreferenceManager preferenceManager;

    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }


    private DataManager(Context context) {
        preferenceManager = PreferenceManager.getInstance(context);
        apiManager = ApiManager.getInstance();

    }

    public static DataManager init(Context context) {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null)
                    instance = new DataManager(context);
            }
        }
        return instance;
    }
    public Call<RecipeResponse> getRecipeData() {

        return apiManager.getRecipeData();
    }

    public void saveDataForWidget(Recipe recipe) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            builder.append(recipe.getIngredients().get(i).getIngredient()).append("\n");
        }

        preferenceManager.setString(PreferenceKeys.INGREDIENTS,builder.toString());
        preferenceManager.setString(PreferenceKeys.RECIPE_NAME,recipe.getName());
        preferenceManager.setInteger(PreferenceKeys.RECIPE_ID,recipe.getId());

    }
}
