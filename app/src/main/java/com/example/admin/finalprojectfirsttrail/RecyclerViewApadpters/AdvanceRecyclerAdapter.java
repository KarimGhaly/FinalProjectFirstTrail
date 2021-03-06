package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.AdvanceInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.ExpenseInfoClass;
import com.example.admin.finalprojectfirsttrail.R;
import com.google.android.gms.common.api.Status;

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

    public interface RecyclerViewInterface{
        public void updateRecord(AdvanceInfoClass advanceInfoClass);
        public void updateRecord(ExpenseInfoClass expenseInfoClass);
    }

    @Nullable
    List<AdvanceInfoClass> AdvancesList;

    @Nullable
    List<ExpenseInfoClass> ExpensesList;

    RecyclerViewInterface recyclerViewInterface;

    private Context context;

    public AdvanceRecyclerAdapter(@Nullable List<AdvanceInfoClass> advancesList,@Nullable List<ExpenseInfoClass> expensesList,RecyclerViewInterface recyclerViewInterface) {
        AdvancesList = advancesList;
        ExpensesList = expensesList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.alert_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        if(AdvancesList != null) {
            final AdvanceInfoClass advance = AdvancesList.get(position);
            holder.txtRecyclerADVDate.setText("Date: " + df.format(advance.getDate()));
            holder.txtRecyclerADVAmount.setText("Amount: " + String.valueOf(advance.getAmount()));
            holder.txtRecyclerADVStatus.setText("Status: " + advance.getStatus());

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(!advance.getStatus().equals("Accepted"))
                    recyclerViewInterface.updateRecord(advance);
                    return true;
                }
            });
        } else
        {
            final ExpenseInfoClass expense = ExpensesList.get(position);
            holder.txtRecyclerADVDate.setText("Date: " + df.format(expense.getDate()));
            holder.txtRecyclerADVAmount.setText("Amount: " + String.valueOf(expense.getAmount()));
            holder.txtRecyclerADVStatus.setText("Status: " + expense.getStatus());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewInterface.updateRecord(expense);
                    return true;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if(AdvancesList!=null) {
            return AdvancesList.size();
        }
        else
        {
            return ExpensesList.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtRecyclerADV_Date)
        TextView txtRecyclerADVDate;
        @BindView(R.id.txtRecyclerADV_Status)
        TextView txtRecyclerADVStatus;
        @BindView(R.id.txtRecyclerADV_Amount)
        TextView txtRecyclerADVAmount;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

