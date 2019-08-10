package com.jetlightstudio.jetalarm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jetlightstudio.jetalarm.Controllers.AlarmSettingActivity;
import com.jetlightstudio.jetalarm.Model.Alarm;
import com.jetlightstudio.jetalarm.R;
import com.jetlightstudio.jetalarm.ToolBox.App;
import com.jetlightstudio.jetalarm.ToolBox.DataBaseManager;

import java.util.ArrayList;

public class CustomAlarmAdapter extends RecyclerView.Adapter<CustomAlarmAdapter.ViewHolder> {
    DataBaseManager dbManager;
    ArrayList<Alarm> alarms;
    int itemPosition = 0;

    public CustomAlarmAdapter(DataBaseManager dbManager, ArrayList<Alarm> alarms) {
        this.dbManager = dbManager;
        this.alarms = alarms;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView timerText;
        LinearLayout layoutBackground;
        Switch enabler;

        ViewHolder(View itemView, int itemPosition) {
            super(itemView);
            timerText = itemView.findViewById(R.id.timeText);
            layoutBackground = itemView.findViewById(R.id.backgroundLayout);
            enabler = itemView.findViewById(R.id.switchID);
            if (itemPosition > 0) itemView.setOnClickListener(this);
            else {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent alarmSettingIntent = new Intent(App.getContext(), AlarmSettingActivity.class);
                        App.getContext().startActivity(alarmSettingIntent);
                    }
                });
            }
        }

        @Override
        public void onClick(View view) {
            //Todo: add clicking logic here
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) App.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(itemPosition > 0 ? R.layout.alarm_view_item : R.layout.adding_alarm_item, null);
        WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - (size.x / 8);
        view.setLayoutParams(new LinearLayout.LayoutParams(width / 3, (int) (width / 1.8)));
        itemPosition++;
        return new ViewHolder(view, itemPosition - 1);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position > 0) {
            holder.enabler.setChecked(alarms.get(position).isActive());
            holder.enabler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //dbManager.alarmActivation(alarms.get(temp).getId(), enabler.isChecked() ? 1 : 0);
                }
            });
            holder.timerText.setText(String.format("%02d:%02d", alarms.get(position).getHour(), alarms.get(position).getMinute()));

        }
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