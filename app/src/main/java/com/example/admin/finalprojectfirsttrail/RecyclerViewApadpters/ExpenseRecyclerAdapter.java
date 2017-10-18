package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.ExpenseClass;
import com.example.admin.finalprojectfirsttrail.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ios on 10/18/17.
 */

public class ExpenseRecyclerAdapter extends RecyclerView.Adapter<ExpenseRecyclerAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    List<ExpenseClass> myList = new ArrayList<>();
    private Context context;

    public ExpenseRecyclerAdapter(List<ExpenseClass> myList) {
        this.myList = myList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

    @Override
    public ExpenseRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.alert_recycler_view, parent,false);
        return new ExpenseRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return myList.size();
    }
}
