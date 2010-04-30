/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iett;

import java.util.regex.Pattern;

/**
 *
 * @author emre
 */
public class Time {
    private int hour;
    private int minute;

    public Time(String text){
        /*
         * Splits the given text according to ":" and sets itself up
         */
        // 19:05
        String[] array = Pattern.compile(":").split(text, 0);
        hour = Integer.parseInt(array[0]);
        minute = Integer.parseInt(array[1]);
    }
    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * @param minute the minute to set
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String toString(){
        return String.format("%02d:%02d", hour, minute);
    }

}
