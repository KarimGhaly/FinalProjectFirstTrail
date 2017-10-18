package com.example.admin.finalprojectfirsttrail.TFragments;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.FragmentClass.PayStubFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.PaySlipInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.PaySlipViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment {
    public static final String TAG = "PayFragmentTAG";
    PayStubFragClass payStubFragClass;
    @BindView(R.id.payStubTitle)
    TextView payStubTitle;
    @BindView(R.id.btnPayStubFrag_advancesRequested)
    Button btnPayStubFragAdvancesRequested;
    @BindView(R.id.btnPayStubFrag_requestAdvance)
    Button btnPayStubFragRequestAdvance;
    @BindView(R.id.lowButtonFrame)
    LinearLayout lowButtonFrame;
    @BindView(R.id.btnPayStubFrag_expenseReport)
    Button btnPayStubFragExpenseReport;
    @BindView(R.id.btnPayStubFrag_submitExpense)
    Button btnPayStubFragSubmitExpense;
    @BindView(R.id.highButtonFrame)
    LinearLayout highButtonFrame;
    @BindView(R.id.financesViewpager)
    ViewPager financesViewpager;
    @BindView(R.id.clPayStubFragment)
    ConstraintLayout clPayStubFragment;
    Unbinder unbinder;


    public PayFragment() {
        // Required empty public constructor
    }

    public PayFragment(PayStubFragClass payStubFragClass) {
        this.payStubFragClass = payStubFragClass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        financesViewpager.setAdapter(new PaySlipViewPagerAdapter(getContext(), payStubFragClass.getPaySlips()));


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
