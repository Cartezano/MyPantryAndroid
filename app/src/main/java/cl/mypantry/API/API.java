package cl.mypantry.API;

import com.google.gson.GsonBuilder;

import cl.mypantry.API.Deserializers.ProductDeserializer;
import cl.mypantry.API.Deserializers.UserDeserializer;
import cl.mypantry.Models.Product;
import cl.mypantry.Models.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "https://api.mypantry.cl/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit setApi(){
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getProductApi(){
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Product.class, new ProductDeserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getUserApi(){
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(User.class, new UserDeserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }
}
