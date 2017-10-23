package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.InterviewInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ios on 10/22/17.
 */

public class MarketingRecyclerAdapter extends RecyclerView.Adapter<MarketingRecyclerAdapter.ViewHolder> {
    Context context;
    List<InterviewInfoClass> interviewList;
    public MarketingRecyclerAdapter(List<InterviewInfoClass> interviewList) {
        this.interviewList = interviewList;
    }

    @Override
    public MarketingRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.interview_list_item, parent,false);
        return new MarketingRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MarketingRecyclerAdapter.ViewHolder holder, int position) {
        holder.clMarketing_InterviewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.elMarketing_InterviewInfo.toggle();
            }
        });
    }

    @Override
    public int getItemCount() {
        return interviewList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clMarketing_InterviewTitle)
        ConstraintLayout clMarketing_InterviewTitle;
        @BindView(R.id.elMarketing_InterviewInfo)
        ExpandableLayout elMarketing_InterviewInfo;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
