package cl.mypantry.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.math.BigInteger;

import cl.mypantry.API.API;
import cl.mypantry.API.ApiResponse.ProductModelResponse;
import cl.mypantry.API.ApiService.ProductService;
import cl.mypantry.Activities.AddPantryActivity;
import cl.mypantry.Activities.AddProductActivity;
import cl.mypantry.Activities.PantryActivity;
import cl.mypantry.Libraries.UtilAndroid;
import cl.mypantry.Models.Product;
import cl.mypantry.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarcodeScanFragment extends Fragment {

    private Button btnScanner;
    private ProductService service;
    private boolean option;
    private Intent intent;

    public BarcodeScanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barcode_scan, container, false);
        option = this.getArguments().getBoolean("option");
        service = API.setApi().create(ProductService.class);
        btnScanner = (Button) view.findViewById(R.id.btnScan);

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannerBarcode(getActivity());
            }
        });

        btnScanner.performClick();
        btnScanner.setFocusable(false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result.getContents() != null) {
            Toast.makeText(this.getActivity(), "Codigo de barra " + result.getContents(), Toast.LENGTH_LONG).show();
            checkProduct(new BigInteger(result.getContents()), this.getActivity(), option);
        }
    }

    public void checkProduct(final BigInteger barcode, final Activity activity, final boolean option) {
        service.checkProduct(barcode).enqueue(new Callback<ProductModelResponse>() {
            @Override
            public void onResponse(Call<ProductModelResponse> call, Response<ProductModelResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Product product = response.body().getProduct();
                    if (option) {
                        Toast.makeText(activity, "El producto existe, pero envia para agregar a la despensa", Toast.LENGTH_LONG).show();
                        intent = UtilAndroid.redirect(activity, AddPantryActivity.class);
                    } else {
                        Toast.makeText(activity, "El producto existe en su despensa.", Toast.LENGTH_LONG).show();
                        intent = UtilAndroid.redirect(activity, PantryActivity.class);
                    }
                    System.out.println(product.getId());
                    intent.putExtra("product_id", product.getId());
                }
                if (response.code() == 404) {
                    if (option) {
                        Toast.makeText(activity, "Se crea el producto porque no existe.", Toast.LENGTH_LONG).show();
                        intent = UtilAndroid.redirect(activity, AddProductActivity.class);
                        intent.putExtra("code", barcode);
                    } else {
                        intent = UtilAndroid.redirect(activity, PantryActivity.class);
                    }
                }

                activity.startActivity(intent);
                activity.finish();
            }

            @Override
            public void onFailure(Call<ProductModelResponse> call, Throwable t) {

            }
        });
    }

    public void scannerBarcode(FragmentActivity fragmentActivity) {
        IntentIntegrator integrator = new IntentIntegrator(fragmentActivity).forSupportFragment(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        integrator.setPrompt(this.getString(R.string.scan_message));
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

}
