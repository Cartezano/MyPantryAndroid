package cl.mypantry.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.mypantry.R;

public class PantryFragment extends Fragment {

    public PantryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pantry, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

        } catch (Exception e){

        }
    }
}
