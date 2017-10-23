package com.example.admin.finalprojectfirsttrail.TFragments;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.FragmentClass.TrainingFragClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.GradedAssignmentInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TodayAssigmentInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters.TrainingRecyclerAdapter;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingFragment extends Fragment {

    public static final String TAG = "TrainginFragmentTAG";
    TrainingFragClass trainingFragClass;
    @BindView(R.id.arc_progress)
    ArcProgress arcProgress;
    @BindView(R.id.btnTraining_githubRepo)
    Button btnTrainingGithubRepo;
    @BindView(R.id.tvTraining_currentAssignmentTitle)
    TextView tvTrainingCurrentAssignmentTitle;
    @BindView(R.id.tvTraining_currentAssignmentDueDate)
    TextView tvTrainingCurrentAssignmentDueDate;
    @BindView(R.id.tvTraining_currentAssignmentDescription)
    TextView tvTrainingCurrentAssignmentDescription;
    @BindView(R.id.rvTraining_Assignments)
    RecyclerView rvTrainingAssignments;
    Unbinder unbinder;


    public TrainingFragment() {
        // Required empty public constructor
    }

    public TrainingFragment(TrainingFragClass trainingFragClass) {
        this.trainingFragClass = trainingFragClass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupUI(trainingFragClass.getTodayAssigmentInfoClass());
        setupRecyclerView(trainingFragClass.getGradeAssignmentList());
        return view;
    }
    public void setupUI(TodayAssigmentInfoClass assignment){
        if(assignment != null) {
            arcProgress.setProgress(Math.round(trainingFragClass.getOverallGrade()));
            if (arcProgress.getProgress() > 85) {
                arcProgress.setTextColor(Color.GREEN);
                arcProgress.setFinishedStrokeColor(Color.GREEN);
            } else if (arcProgress.getProgress() > 60) {
                arcProgress.setTextColor(Color.YELLOW);
                arcProgress.setFinishedStrokeColor(Color.YELLOW);
            } else {
                arcProgress.setTextColor(Color.RED);
                arcProgress.setFinishedStrokeColor(Color.RED);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            tvTrainingCurrentAssignmentTitle.setText(assignment.getTitle());
            tvTrainingCurrentAssignmentDueDate.setText(sdf.format(assignment.getDueDate()));
            tvTrainingCurrentAssignmentDescription.setText(assignment.getDescription());
        }

    }

    public void setupRecyclerView(List<GradedAssignmentInfoClass> gradesList)
    {
        TrainingRecyclerAdapter trainingRecyclerAdapter = new TrainingRecyclerAdapter(gradesList);
        rvTrainingAssignments.setAdapter(trainingRecyclerAdapter);
        rvTrainingAssignments.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTrainingAssignments.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnTraining_githubRepo)
    public void onViewClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trainingFragClass.getTodayAssigmentInfoClass().getSylbusLink()));
        startActivity(browserIntent);
    }
}
