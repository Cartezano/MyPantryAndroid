package cl.mypantry.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cl.mypantry.API.API;
import cl.mypantry.API.ApiResponse.PantryListResponse;
import cl.mypantry.API.ApiResponse.ProductModelResponse;
import cl.mypantry.API.ApiService.ProductService;
import cl.mypantry.API.ApiService.UserService;
import cl.mypantry.Libraries.PantryAdapter;
import cl.mypantry.Libraries.UtilPreference;
import cl.mypantry.Models.Pantry;
import cl.mypantry.Models.PantryProduct;
import cl.mypantry.Models.Product;
import cl.mypantry.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PantryFragment extends Fragment {

    protected ListView listView;
    private List<PantryProduct> pantryProductList;
    private UserService userService;
    private ProductService productService;
    private SharedPreferences preferences;

    public PantryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pantry, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_pantry);

        userService = API.setApi().create(UserService.class);
        productService = API.setApi().create(ProductService.class);
        preferences = this.getActivity().getSharedPreferences("MyPantry", Context.MODE_PRIVATE);
        pantryProductList = new ArrayList<>();

        pantryList(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

        } catch (Exception e){

        }
    }

    public void pantryList(final View view) {
        Log.d("Success", "Consulta: " + UtilPreference.getPreferenceId(preferences));
        userService.getProducts(UtilPreference.getPreferenceId(preferences)).enqueue(new Callback<PantryListResponse>() {
            @Override
            public void onResponse(Call<PantryListResponse> call, Response<PantryListResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    for (Pantry pantry :response.body().getPantry()
                         ) {
                        Log.d("Success", "ID del Producto: " + pantry.getProductId());
                        productList(pantry, view);
                    }
                } else {
                    Log.d("Error", "Mensaje: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PantryListResponse> call, Throwable t) {
                Log.d("Error", "Mensaje: " + t.getMessage().toString());
            }
        });
    }

    public void productList(final Pantry pantry, final View view) {
        productService.getProduct(pantry.getProductId()).enqueue(new Callback<ProductModelResponse>() {
            @Override
            public void onResponse(Call<ProductModelResponse> call, Response<ProductModelResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    Product product = response.body().getProduct();

                    Log.d("Success", "ID del Producto: " + product.getId());

                    PantryProduct pantryProduct = new PantryProduct(product.getName(), product.getBrand(), pantry.getQuality(), pantry.getExpirationDate());
                    pantryProductList.add(pantryProduct);


                    setList(view, pantryProductList);
                } else {
                    Log.d("Error", "Mensaje: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductModelResponse> call, Throwable t) {
                Log.d("Error", "Mensaje: " + t.getMessage().toString());
            }
        });
    }

    public void setList(View view, List<PantryProduct> pantryProduct){
        PantryAdapter pantryAdapter = new PantryAdapter(view.getContext(), R.layout.list_view_item_pantry, pantryProduct);
        listView.setAdapter(pantryAdapter);
    }
}
