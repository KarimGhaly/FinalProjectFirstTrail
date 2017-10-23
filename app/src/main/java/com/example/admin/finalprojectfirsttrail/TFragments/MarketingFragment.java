package com.example.admin.finalprojectfirsttrail.TFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.finalprojectfirsttrail.FragmentClass.InterviewFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.InsuranceInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.InterviewInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.MarketingRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketingFragment extends Fragment {
    InterviewFragClass interviewFragClass;
    @BindView(R.id.rvInterviewList)
    RecyclerView rvInterviewList;
    Unbinder unbinder;


    public MarketingFragment() {
        // Required empty public constructor
    }

    public MarketingFragment(InterviewFragClass interviewFragClass) {
        this.interviewFragClass = interviewFragClass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marketing, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupRecyclerView(interviewFragClass.getInterviewList());
        return view;
    }

    private void setupRecyclerView(List<InterviewInfoClass> interviewList) {
        MarketingRecyclerAdapter adapter = new MarketingRecyclerAdapter(interviewList);
        rvInterviewList.setAdapter(adapter);
        rvInterviewList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInterviewList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
