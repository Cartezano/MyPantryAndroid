package cl.mypantry.API.ApiService;

import java.math.BigInteger;

import cl.mypantry.API.ApiResponse.ProductListResponse;
import cl.mypantry.API.ApiResponse.ProductModelResponse;
import cl.mypantry.Models.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {

    @GET("products")
    Call<ProductListResponse> getProducts();

    @POST("products")
    Call<ProductModelResponse> createProduct(
            @Body Product product
    );

    @GET("products/{product}")
    Call<ProductModelResponse> getProduct(
            @Path("product") int id
    );

    @GET("products/{product}/barcode")
    Call<ProductModelResponse> checkProduct(
            @Path("product") BigInteger barcode
    );
}
