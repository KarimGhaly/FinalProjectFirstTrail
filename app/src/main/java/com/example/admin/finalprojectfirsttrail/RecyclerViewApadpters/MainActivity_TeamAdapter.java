package com.example.admin.finalprojectfirsttrail.RecyclerViewApadpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.finalprojectfirsttrail.R;

import java.util.List;

/**
 * Created by Admin on 10/13/2017.
 */

public class MainActivity_TeamAdapter extends RecyclerView.Adapter<MainActivity_TeamAdapter.ViewHolder> {
    Context context;
    List<String> TeamList;

    public MainActivity_TeamAdapter(List<String> teamList) {
        TeamList = teamList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.accoun_team_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String teamMember = TeamList.get(position);
        holder.txtTeamMember.setText(teamMember);
    }

    @Override
    public int getItemCount() {
        return TeamList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTeamMember;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTeamMember = itemView.findViewById(R.id.txtAccount_TeamList);
        }
    }
}
