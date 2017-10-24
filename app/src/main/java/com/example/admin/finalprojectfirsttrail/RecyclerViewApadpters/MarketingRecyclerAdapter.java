package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.InterviewInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.text.SimpleDateFormat;
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.interview_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final InterviewInfoClass interview = interviewList.get(position);
        holder.tvInterviewsInterviewTitle.setText(interview.getInterviewTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        holder.tvInterviewsInterviewDate.setText(sdf.format(interview.getInterviewDateTime()));
        holder.tvInterviewsInterviewerNames.setText(interview.getInterviewName());
        holder.tvInterviewsMentorName.setText(interview.getMentor());
        holder.tvInterviewsClient.setText(interview.getClientName());
        holder.tvInterviewsVendor.setText(interview.getVendorName());
        holder.tvInterviewsProjectCityAndState.setText(interview.getProjectCityState());
        holder.tvInterviewsProjectDuration.setText(interview.getProjectDuration());
        holder.tvInterviewsAvailabilityDate.setText(interview.getAvailabilityDate());
        holder.tvInterviewsClientWebsite.setText(interview.getClientWebsite());
        holder.tvInterviewsBtnMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(interview.getPdfMoreDetailsLink()));
                context.startActivity(browserIntent);
            }
        });
        holder.clMarketingInterviewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.elMarketingInterviewInfo.toggle();
            }
        });
    }

    @Override
    public int getItemCount() {
        return interviewList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvInterviews_interviewTitle)
        TextView tvInterviewsInterviewTitle;
        @BindView(R.id.tvInterviews_interviewDate)
        TextView tvInterviewsInterviewDate;

        @BindView(R.id.clMarketing_InterviewTitle)
        ConstraintLayout clMarketingInterviewTitle;

        @BindView(R.id.tvInterviews_interviewerNames)
        TextView tvInterviewsInterviewerNames;

        @BindView(R.id.tvInterviews_mentorName)
        TextView tvInterviewsMentorName;

        @BindView(R.id.tvInterviews_client)
        TextView tvInterviewsClient;

        @BindView(R.id.tvInterviews_vendor)
        TextView tvInterviewsVendor;

        @BindView(R.id.tvInterviews_projectCityAndState)
        TextView tvInterviewsProjectCityAndState;

        @BindView(R.id.tvInterviews_projectDuration)
        TextView tvInterviewsProjectDuration;

        @BindView(R.id.tvInterviews_availabilityDate)
        TextView tvInterviewsAvailabilityDate;

        @BindView(R.id.tvInterviews_clientWebsite)
        TextView tvInterviewsClientWebsite;
        @BindView(R.id.tvInterviews_btnMoreDetails)
        Button tvInterviewsBtnMoreDetails;

        @BindView(R.id.elMarketing_InterviewInfo)
        ExpandableLayout elMarketingInterviewInfo;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
