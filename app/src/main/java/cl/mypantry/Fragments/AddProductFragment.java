package cl.mypantry.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import cl.mypantry.R;

public class AddProductFragment extends Fragment {

    private Button btnScanner;
    private EditText txtName, txtBrand, txtQuality, txtBarcode;

    public AddProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        txtBarcode = (EditText) view.findViewById(R.id.barcode);

        txtBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannerBarcode(getActivity());
            }
        });

        txtBarcode.performClick();
        txtBarcode.setFocusable(false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

        } catch (Exception e) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String barcode = result.getContents();

        if (result.getContents() == null) {
            Toast.makeText(this.getActivity(), this.getString(R.string.scan_close), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.getActivity(), result.getContents(), Toast.LENGTH_LONG).show();
            txtBarcode.setText(barcode);
        }
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
