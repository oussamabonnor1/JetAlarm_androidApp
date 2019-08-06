package com.jetlightstudio.jetalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class AlarmCreationActivity extends AppCompatActivity {

    ListView listView;
    CustomStoryAdapter c;
    String[] hints = {
            "Sleep early to wake up early.",
            "Hope you're having a great time!",
            "Don't forget to drink plenty of water!",
            "Did you brush your teeth?",
            "Wear comfortable clothes to bed!",
            "Just tell me when i should notify you!",
            "Hello, nice to see you again!"
    };
    TextView hintText;
    DataBaseManager dbManager;
    ArrayList<Alarm> alarms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_creation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dbManager = new DataBaseManager(getApplicationContext(), null);
        alarms = dbManager.getAlarms();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmCreationActivity.this, AlarmSettingActivity.class);
                intent.putExtra("id", alarms.size() + 1);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.listView);
        c = new CustomStoryAdapter();
        listView.setAdapter(c);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        alarms.clear();
        alarms = dbManager.getAlarms();
        c.notifyDataSetChanged();
        listView.setAdapter(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void intentGo(View v) {
        Intent i = new Intent(getApplicationContext(), AlarmSettingActivity.class);
        startActivity(i);
    }

    class CustomStoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return alarms.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.alarm_layout, null);

            TextView time = view.findViewById(R.id.timeText);
            TextView week = view.findViewById(R.id.weekText);
            final Switch enabler = view.findViewById(R.id.switchID);
            enabler.setChecked(alarms.get(i).isActive());
            final int temp = i;
            enabler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbManager.alarmActivation(alarms.get(temp).getId(), enabler.isChecked() ? 1 : 0);
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            time.setText(String.format("%02d:%02d", alarms.get(i).getHour(), alarms.get(i).getMinute()));
            week.setText("S S M T W T F");
            return view;
        }

    }
}
