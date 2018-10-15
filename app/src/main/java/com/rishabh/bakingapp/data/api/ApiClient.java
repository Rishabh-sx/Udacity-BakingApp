package com.rishabh.bakingapp.data.api;

import com.rishabh.bakingapp.pojo.Recipe.RecipeResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

interface ApiClient {

    String ENDPOINT = "https://test-app-rishabh.herokuapp.com/";

    @GET("recipe")
    Call<RecipeResponse> getRecipeData();
}
