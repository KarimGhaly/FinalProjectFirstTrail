package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.GradedAssignmentInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ios on 10/22/17.
 */

public class TrainingRecyclerAdapter extends RecyclerView.Adapter<TrainingRecyclerAdapter.ViewHolder> {
    Context context;
    List<GradedAssignmentInfoClass> assignmentList;

    public TrainingRecyclerAdapter(List<GradedAssignmentInfoClass> assignmentList) {
        this.assignmentList = assignmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GradedAssignmentInfoClass grade = assignmentList.get(position);
        holder.tvTrainingPastAssignmentTitle.setText(grade.getTitleAssignment());
        holder.tvTrainingPastAssignmentGrade.setText(String.valueOf(grade.getGrade()));
        holder.tvTrainingPastAssignmentFeedback.setText(grade.getFeedback());
        holder.clTrainingAssignmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.elTrainingAssignmentCard.toggle();
            }
        });
    }
    @Override
    public int getItemCount() {
        return assignmentList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTraining_pastAssignmentTitle)
        TextView tvTrainingPastAssignmentTitle;
        @BindView(R.id.tvTraining_pastAssignmentGrade)
        TextView tvTrainingPastAssignmentGrade;
        @BindView(R.id.clTraining_AssignmentCard)
        ConstraintLayout clTrainingAssignmentCard;
        @BindView(R.id.tvTraining_pastAssignmentFeedback)
        TextView tvTrainingPastAssignmentFeedback;
        @BindView(R.id.elTraining_AssignmentCard)
        ExpandableLayout elTrainingAssignmentCard;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}