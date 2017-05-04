package com.ferraro.myjiujitsujournal.mjjj;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Nick on 2/26/2017.
 */
public class Gym implements Serializable{

    private String name;
    private String id;
    private int scheduleImageName;
    private String location;
    private Journal journal;
    private int iconImageName;
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

    public int getScheduleImageName() {
        return scheduleImageName;
    }

    public void setScheduleImageName(int scheduleImageName) {
        this.scheduleImageName = scheduleImageName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIconImageName() {
        return iconImageName;
    }

    public void setIconImageName(int iconImageName) {
        this.iconImageName = iconImageName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gym gym = (Gym) o;

        return !(getId() != null ? !getId().equals(gym.getId()) : gym.getId() != null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
