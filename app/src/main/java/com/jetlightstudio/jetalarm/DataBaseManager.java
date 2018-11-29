package com.jetlightstudio.jetalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class DataBaseManager extends SQLiteOpenHelper {
    private static String dbName = "alarms.db";
    private static int dbVersion = 1;
    private static String tableName = "alarms";
    private static String columnHours = "hours";
    private static String columId = "id";
    private static String columnMinutes = "minutes";
    private static String columnIsActive = "isActive";


    public DataBaseManager(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create Table " + tableName + "("
                + columId + " Integer,"
                + columnHours + " Integer, "
                + columnMinutes + " Integer,"
                + columnIsActive + " Integer," +
                "Primary Key(" + columId + ")"
                + ");";
        sqLiteDatabase.execSQL(query);
    }

    public void createAlarm(Context context, Calendar calendar) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnHours, calendar.get(Calendar.HOUR_OF_DAY));
        contentValues.put(columnMinutes, calendar.get(Calendar.MINUTE));
        contentValues.put(columnIsActive, 1);
        sqLiteDatabase.insert(tableName, null, contentValues);
        Toast.makeText(context, "Alarm will start in " + AlarmSettingActivity.estimatedTime(calendar), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.close();
    }

    public ArrayList<Alarm> getAlarms() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select * from " + tableName, null);
        ArrayList<Alarm> s = new ArrayList<>();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            s.add(new Alarm(c.getInt(c.getColumnIndex(columnHours)),
                    c.getInt(c.getColumnIndex(columnMinutes)),
                    c.getInt(c.getColumnIndex(columId)),
                    c.getInt(c.getColumnIndex(columnIsActive)) == 1));
            c.moveToNext();
        }
        c.close();
        db.close();
        return s;
    }

    public void alarmActivation(int id, int isActive) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + tableName + " Set " + columnIsActive + "=" + isActive + " where " + columId + "=" + id + ";";
        db.execSQL(query);
    }

    public void alarmUpdate(int id, Calendar calendar) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnHours, calendar.get(Calendar.HOUR_OF_DAY));
        contentValues.put(columnMinutes, calendar.get(Calendar.MINUTE));
        contentValues.put(columnIsActive, 1);
        sqLiteDatabase.update(tableName, contentValues, "WHERE id = " + id, null);
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }
}
