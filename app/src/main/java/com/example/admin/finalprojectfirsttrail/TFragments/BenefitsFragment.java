package com.example.admin.finalprojectfirsttrail.TFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.finalprojectfirsttrail.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BenefitsFragment extends Fragment {


    public BenefitsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benefits, container, false);
    }

}
