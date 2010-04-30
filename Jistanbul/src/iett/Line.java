/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iett;

import java.util.ArrayList;

/**
 *
 * @author emre
 */
public class Line {
    private String code;
    private ArrayList<Time> timeList;
    
    public Line(String code){
        this.code = code;
        timeList = new ArrayList<Time>();
    }

    /**
     * @return the timeList
     */
    public ArrayList<Time> getTimeList() {
        return timeList;
    }

    /**
     * @param timeList the timeList to set
     */
    public void setTimeList(ArrayList<Time> timeList) {
        this.timeList = timeList;
    }
    


}
