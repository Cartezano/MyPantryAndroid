package cl.mypantry.API.ApiService;

import cl.mypantry.API.ApiResponse.PantryListResponse;
import cl.mypantry.API.ApiResponse.UserModelResponse;
import cl.mypantry.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Call<User> getUsers();

    @GET("users/{user}")
    Call<User> getUser(
            @Path("user") int id
    );

    @POST("register")
    Call<User> createUser(
            @Body User user
    );

    @POST("login")
    @FormUrlEncoded
    Call<UserModelResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("users/{user}/products")
    Call<PantryListResponse> getProducts(
            @Path("user") int id
    );

    @DELETE("users/{user}")
    Call<User> deleteUser(
            @Path("user") int id
    );
}
