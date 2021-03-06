package com.jetlightstudio.jetalarm.Controllers;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jetlightstudio.jetalarm.Adapters.CustomAlarmAdapter;
import com.jetlightstudio.jetalarm.Model.Alarm;
import com.jetlightstudio.jetalarm.R;
import com.jetlightstudio.jetalarm.ToolBox.DataBaseManager;

import java.util.ArrayList;
import java.util.Random;

public class AlarmCreationActivity extends AppCompatActivity {

    RecyclerView recycleView;
    CustomAlarmAdapter c;
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

        dbManager = new DataBaseManager(getApplicationContext(), null);
        alarms.add(0, new Alarm());
        alarms.addAll(dbManager.getAlarms());
        System.out.println(alarms.size());
        hintText = findViewById(R.id.hintText);
        hintText.setText(hints[new Random().nextInt(hints.length)]);

        recycleView = findViewById(R.id.recycleView);
        c = new CustomAlarmAdapter(dbManager, alarms);
        recycleView.setAdapter(c);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10, 5, 10, 5);
            }
        });
        recycleView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        /*alarms.clear();
        alarms = dbManager.getAlarms();
        c.notifyDataSetChanged();
        recycleView.setAdapter(c);*/
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

}
