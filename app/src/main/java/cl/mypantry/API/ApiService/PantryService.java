package cl.mypantry.API.ApiService;

import java.util.Date;

import cl.mypantry.API.ApiResponse.PantryListResponse;
import cl.mypantry.API.ApiResponse.PantryModelResponse;
import cl.mypantry.Models.Pantry;

import cl.mypantry.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PantryService {
    @GET("pantries")
    Call<PantryListResponse> getPantries();

    @POST("pantries")
    Call<PantryModelResponse> createPantry(
            @Body Pantry product
    );

    @GET("pantries/{pantry}")
    Call<PantryModelResponse> getPantry(@Path("pantry") String pantry);

    @POST("pantries/count")
    Call<PantryModelResponse> addProduct(
            @Body Pantry pantry
    );

    @POST("pantries/discount")
    Call<PantryModelResponse> removeProduct(
            @Body Pantry pantry
    );
}
