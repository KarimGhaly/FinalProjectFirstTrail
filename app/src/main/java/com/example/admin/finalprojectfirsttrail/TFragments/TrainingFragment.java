package com.example.admin.finalprojectfirsttrail.TFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.finalprojectfirsttrail.InfoClass.TrainingAssignmentInfo;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.TrainingRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingFragment extends Fragment {


    public TrainingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        RecyclerView rvTraining_Assignments = view.findViewById(R.id.rvTraining_Assignments) ;
        List<TrainingAssignmentInfo> assignmentList = new ArrayList<>();
        assignmentList.add(new TrainingAssignmentInfo());
        assignmentList.add(new TrainingAssignmentInfo());
        assignmentList.add(new TrainingAssignmentInfo());
        assignmentList.add(new TrainingAssignmentInfo());
        assignmentList.add(new TrainingAssignmentInfo());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        TrainingRecyclerAdapter adapter = new TrainingRecyclerAdapter(assignmentList);
        rvTraining_Assignments.setAdapter(adapter);
        rvTraining_Assignments.setLayoutManager(manager);
        rvTraining_Assignments.setItemAnimator(animator);

        return view;
    }

}
