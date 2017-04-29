package com.ferraro.myjiujitsujournal.mjjj;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Nick on 2/26/2017.
 */
public class Gym implements Serializable{

    private String name;
    private String id;
    private String scheduleImageName;
    private String location;
    private Journal journal;
    private String iconImageName;
    private String theme;

    public Gym(String name) {
        this.name = name;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheduleImageName() {
        return scheduleImageName;
    }

    public void setScheduleImageName(String scheduleImageName) {
        this.scheduleImageName = scheduleImageName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIconImageName() {
        return iconImageName;
    }

    public void setIconImageName(String iconImageName) {
        this.iconImageName = iconImageName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
