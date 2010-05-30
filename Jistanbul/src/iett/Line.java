/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iett;

import utils.TimeList;

/**
 *
 * @author emre
 */
public class Line {

    private String code;
    private String from, to;
    private TimeList[] timetable;

    public Line(String code) {
        this.code = code;
    }

    /**
     * @return the timetable
     */
    public TimeList[] getTimetable() {
        return timetable;
    }

    /**
     * @param timetable the timetable to set
     */
    public void setTimetable(TimeList[] timetable) {
        this.timetable = timetable;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
}
