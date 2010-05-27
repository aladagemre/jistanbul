/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iett;

import utils.StringOperations;

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
        String[] array = StringOperations.split(text, ":");
        int delimiterIndex = text.indexOf(":");
        
        hour = Integer.parseInt(text.substring(0,delimiterIndex));
        minute = Integer.parseInt(text.substring(delimiterIndex+1));
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
        String result="";
        if (hour < 9) result += "0";
        result += hour;
        result += ":";
        if (minute < 9) result += "0";
        result += minute;
        return result;
    }

}
