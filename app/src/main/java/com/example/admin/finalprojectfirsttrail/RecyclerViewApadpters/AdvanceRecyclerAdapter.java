package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.AdvanceInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ios on 10/18/17.
 */

public class AdvanceRecyclerAdapter extends RecyclerView.Adapter<AdvanceRecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    List<AdvanceInfoClass> myList = new ArrayList<>();
    private Context context;

    public AdvanceRecyclerAdapter(List<AdvanceInfoClass> myList) {
        this.myList = myList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.alert_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AdvanceInfoClass advance = myList.get(position);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        holder.txtRecyclerADVDate.setText(df.format(advance.getDate()));
        holder.txtRecyclerADVAmount.setText(String.valueOf(advance.getAmount()));
        holder.txtRecyclerADVDesc.setText(advance.getStatus());
        holder.txtRecyclerADVDesc.setText(advance.getDescriction());
    }

    @Override
    public int getItemCount() {

        return myList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtRecyclerADV_Date)
        TextView txtRecyclerADVDate;
        @BindView(R.id.txtRecyclerADV_Status)
        TextView txtRecyclerADVStatus;
        @BindView(R.id.txtRecyclerADV_Amount)
        TextView txtRecyclerADVAmount;
        @BindView(R.id.txtRecyclerADV_Desc)
        TextView txtRecyclerADVDesc;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

