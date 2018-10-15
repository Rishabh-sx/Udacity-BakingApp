package com.rishabh.bakingapp.data.api;

import com.rishabh.bakingapp.pojo.Recipe.RecipeResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class ApiManager {

    private static final ApiManager instance = new ApiManager();
    private final ApiClient apiClient;

    private ApiManager() {
        apiClient = getRetrofitService();
    }

    public static ApiManager getInstance() {
        return instance;
    }

    private static ApiClient getRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiClient.ENDPOINT)
                .build();

        return retrofit.create(ApiClient.class);
    }

    public Call<RecipeResponse> getRecipeData() {
          return  apiClient.getRecipeData();
    }


    /*public Call<RandomDog> fetchNextRandom() {
        return apiClient.fetchRandomDog();
    }*/
}
