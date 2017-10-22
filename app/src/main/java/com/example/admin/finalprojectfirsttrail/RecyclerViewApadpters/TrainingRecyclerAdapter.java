package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.finalprojectfirsttrail.InfoClass.InterviewInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TrainingAssignmentInfo;
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
    List<TrainingAssignmentInfo> assignmentList;
    public TrainingRecyclerAdapter(List<TrainingAssignmentInfo> assignmentList) {
        this.assignmentList = assignmentList;
    }

    @Override
    public TrainingRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_list_item, parent,false);
        return new TrainingRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrainingRecyclerAdapter.ViewHolder holder, int position) {

        holder.clTraining_AssignmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.elTraining_AssignmentCard.toggle();
            }
        });

    }


    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clTraining_AssignmentCard)
        ConstraintLayout clTraining_AssignmentCard;
        @BindView(R.id.elTraining_AssignmentCard)
        ExpandableLayout elTraining_AssignmentCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}