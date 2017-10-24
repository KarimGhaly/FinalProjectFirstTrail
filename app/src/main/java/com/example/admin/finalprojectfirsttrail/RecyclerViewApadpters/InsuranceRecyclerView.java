package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.InsuranceInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 10/21/2017.
 */

public class InsuranceRecyclerView extends RecyclerView.Adapter<InsuranceRecyclerView.ViewHolder> {

    Context context;
    List<InsuranceInfoClass> insurnceList;

    public InsuranceRecyclerView(List<InsuranceInfoClass> insurnceList) {
        this.insurnceList = insurnceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.benefits_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InsuranceInfoClass insurance = insurnceList.get(position);
        holder.tvBenefitsBenefitTitle.setText(insurance.getType());
        holder.tvBenefitsBenefitCarrier.setText(insurance.getCompanyName());
        holder.tvBenefitsBenfitPlantxt.setText(insurance.getPackageName());
    }

    @Override
    public int getItemCount() {
        return insurnceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBenefits_benefitTitle)
        TextView tvBenefitsBenefitTitle;
        @BindView(R.id.tvBenefits_benefitCarrier)
        TextView tvBenefitsBenefitCarrier;
        @BindView(R.id.tvBenefits_benefitPlan)
        TextView tvBenefitsBenfitPlantxt;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
