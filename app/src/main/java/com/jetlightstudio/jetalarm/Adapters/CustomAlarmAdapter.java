package com.jetlightstudio.jetalarm.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jetlightstudio.jetalarm.Model.Alarm;
import com.jetlightstudio.jetalarm.R;
import com.jetlightstudio.jetalarm.ToolBox.App;
import com.jetlightstudio.jetalarm.ToolBox.DataBaseManager;

import java.util.ArrayList;

public class CustomAlarmAdapter extends RecyclerView.Adapter<CustomAlarmAdapter.ViewHolder> {
    DataBaseManager dbManager;
    ArrayList<Alarm> alarms;

    public CustomAlarmAdapter(DataBaseManager dbManager, ArrayList<Alarm> alarms) {
        this.dbManager = dbManager;
        this.alarms = alarms;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView timerText;
        LinearLayout layoutBackground;
        Switch enabler;

        ViewHolder(View itemView) {
            super(itemView);
            timerText = itemView.findViewById(R.id.timeText);
            layoutBackground = itemView.findViewById(R.id.backgroundLayout);
            enabler = itemView.findViewById(R.id.switchID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Todo: add clicking logic here
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alarm_view_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.enabler.setChecked(alarms.get(position).isActive());
        holder.enabler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dbManager.alarmActivation(alarms.get(temp).getId(), enabler.isChecked() ? 1 : 0);
            }
        });
        holder.timerText.setText(String.format("%02d:%02d", alarms.get(position).getHour(), alarms.get(position).getMinute()));
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}