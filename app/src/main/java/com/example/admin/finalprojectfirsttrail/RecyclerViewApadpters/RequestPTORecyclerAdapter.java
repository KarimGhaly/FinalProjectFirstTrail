package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.InfoClass.PTORequestInfoClass;
import com.example.admin.finalprojectfirsttrail.R;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 10/21/2017.
 */

public class RequestPTORecyclerAdapter extends RecyclerView.Adapter<RequestPTORecyclerAdapter.ViewHolder> {
    public interface RequestPTORecyclerAdapterInterface {
        void updatePTORequest(PTORequestInfoClass pto);
    }

    Context context;
    List<PTORequestInfoClass> ptolist;
    RequestPTORecyclerAdapterInterface requestInterface;

    public RequestPTORecyclerAdapter(List<PTORequestInfoClass> ptolist, RequestPTORecyclerAdapterInterface requestInterface) {
        this.ptolist = ptolist;
        this.requestInterface = requestInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.benefits_item_pto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PTORequestInfoClass pto = ptolist.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        holder.recyclerPTODateFrom.setText("From: " + sdf.format(pto.getDateFrom()));
        holder.recyclerPTODateTo.setText("To: " + sdf.format(pto.getDateTo()));
        holder.recyclerPTODateRequest.setText("Date Request: " + sdf.format(pto.getDateRequest()));
        holder.recyclerPTOStatus.setText("Status: "+pto.getStatus());
        if (!pto.getStatus().equals("Used")) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    requestInterface.updatePTORequest(pto);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return ptolist.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerPTO_dateFrom)
        TextView recyclerPTODateFrom;
        @BindView(R.id.recyclerPTO_dateTo)
        TextView recyclerPTODateTo;
        @BindView(R.id.recyclerPTO_dateRequest)
        TextView recyclerPTODateRequest;
        @BindView(R.id.recyclerPTO_Status)
        TextView recyclerPTOStatus;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
