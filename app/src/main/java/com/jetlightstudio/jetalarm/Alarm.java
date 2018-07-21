package com.jetlightstudio.jetalarm;

public class Alarm {
    private int hour, minute, id;
    private boolean isActive;

    public Alarm(int hour, int minute, int id, boolean isActive) {
        this.hour = hour;
        this.minute = minute;
        this.id = id;
        this.isActive = isActive;
    }

    public Alarm() {
    }

    public int getId() {
        return id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
