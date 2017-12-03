package cl.mypantry.API.ApiService;

import java.util.Date;

import cl.mypantry.API.ApiResponse.PantryListResponse;
import cl.mypantry.API.ApiResponse.PantryModelResponse;
import cl.mypantry.Models.Pantry;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PantryService {
    @GET("pantries/{pantry}")
    Call<Pantry> getPantry(@Path("pantry") String pantry);

    @GET("pantries")
    Call<PantryListResponse> getPantries();

    @GET("pantries/products/{product}/users/{user}/count")
    Call<PantryModelResponse> count(
            @Path("product") int product_id,
            @Path("user") int user_id,
            @Field("expiration_date") Date expiration_date,
            @Field("quality") int quality
    );

    @GET("pantries/products/{product}/users/{user}/discount")
    Call<PantryModelResponse> discount(
            @Path("product") int product_id,
            @Path("user") int user_id,
            @Field("expiration_date") Date expiration_date,
            @Field("quality") int quality
    );
}
