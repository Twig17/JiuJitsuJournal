package com.ferraro.myjiujitsujournal.database;

import com.ferraro.myjiujitsujournal.mjjj.Gym;
import com.ferraro.myjiujitsujournal.mjjj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 3/7/2017.
 */
public class CreateDefaultGyms {

    static List<Gym> allGyms;

    public static List<Gym> createGyms() {
        //If we already created the gyms don't need to create them again
        if(allGyms != null) {
            return allGyms;
        }

        allGyms = new ArrayList<Gym>();

        Gym rgjcGym = new Gym("Renzo Gracie Jersey City");
        rgjcGym.setIconImageName(R.drawable.rgjc_icon);
        rgjcGym.setScheduleImageName(R.drawable.rgjc_schedule);

        Gym rgnyGym = new Gym("Renzo Gracie New York City");

        allGyms.add(rgjcGym);
        allGyms.add(rgnyGym);
        return allGyms;
    }

}
