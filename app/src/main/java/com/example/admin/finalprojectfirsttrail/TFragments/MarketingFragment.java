package com.example.admin.finalprojectfirsttrail.TFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.InterviewInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TeamInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.MainActivity_TeamAdapter;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.MarketingRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketingFragment extends Fragment {


    public MarketingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marketing, container, false);

        RecyclerView rvInterviewList = view.findViewById(R.id.rvInterviewList);
        List<InterviewInfoClass> interviewList = new ArrayList<>();
        interviewList.add(new InterviewInfoClass());
        interviewList.add(new InterviewInfoClass());
        interviewList.add(new InterviewInfoClass());
        interviewList.add(new InterviewInfoClass());
        interviewList.add(new InterviewInfoClass());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        MarketingRecyclerAdapter marketingRecyclerAdapter = new MarketingRecyclerAdapter(interviewList);
        rvInterviewList.setAdapter(marketingRecyclerAdapter);
        rvInterviewList.setLayoutManager(layoutManager);
        rvInterviewList.setItemAnimator(itemAnimator);

        return view;
    }

}
