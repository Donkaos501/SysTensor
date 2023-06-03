
package de.donkaos.systensor;

import java.util.concurrent.TimeUnit;

public class Wait {


    public static void nanoSec(int s){
        try {
            TimeUnit.NANOSECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void milliSec(int s){
        try {
            TimeUnit.MILLISECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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

    public static void hours(int s){
        try {
            TimeUnit.HOURS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
