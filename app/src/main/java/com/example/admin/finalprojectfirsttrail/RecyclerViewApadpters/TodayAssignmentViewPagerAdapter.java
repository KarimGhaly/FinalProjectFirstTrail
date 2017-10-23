package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.TodayAssigmentInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 10/23/2017.
 */

public class TodayAssignmentViewPagerAdapter extends PagerAdapter {
    Context context;
    List<TodayAssigmentInfoClass> assignmentsList;
    getPosition getPosition;
    @BindView(R.id.tvTraining_currentAssignmentTitle)
    TextView tvTrainingCurrentAssignmentTitle;
    @BindView(R.id.tvTraining_currentAssignmentDueDate)
    TextView tvTrainingCurrentAssignmentDueDate;
    @BindView(R.id.textView30)
    TextView textView30;
    @BindView(R.id.tvTraining_currentAssignmentDescription)
    TextView tvTrainingCurrentAssignmentDescription;

    public interface getPosition{
        void getPosition(int position);
    }


    public TodayAssignmentViewPagerAdapter(Context context, List<TodayAssigmentInfoClass> assignmentsList, TodayAssignmentViewPagerAdapter.getPosition getPosition) {
        this.context = context;
        this.assignmentsList = assignmentsList;
        this.getPosition = getPosition;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TodayAssigmentInfoClass todayAssignemt = assignmentsList.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.today_assignment_item, container, false);
        ButterKnife.bind(this,view);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        tvTrainingCurrentAssignmentTitle.setText(todayAssignemt.getTitle());
        tvTrainingCurrentAssignmentDueDate.setText(sdf.format(todayAssignemt.getDueDate()));
        tvTrainingCurrentAssignmentDescription.setText(todayAssignemt.getDescription());
        getPosition.getPosition(position);
        container.addView(view);
        return view;

    }

    @Override
    public int getCount() {
        return assignmentsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
