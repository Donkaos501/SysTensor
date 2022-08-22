package de.donkaos;

import java.util.concurrent.TimeUnit;

public class Wait {


    public static void seconds(int s){
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void minutes(int s){
        try {
            TimeUnit.MINUTES.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
