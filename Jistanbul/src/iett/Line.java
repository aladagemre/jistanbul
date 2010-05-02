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
    private String from, to;
    private ArrayList<ArrayList<Time>> timetable;
    private ArrayList<Time[]> rowBasedTimeTable=null;
    
    public Line(String code){
        this.code = code;
        //timetable = new ArrayList<ArrayList<Time>>();
    }

    /**
     * @return the timetable
     */
    public ArrayList<ArrayList<Time>> getTimetable() {
        return timetable;
    }

    /**
     * @param timetable the timetable to set
     */
    public void setTimetable(ArrayList<ArrayList<Time>> timetable) {
        this.timetable = timetable;
    }

    public ArrayList<Time[]> getRowBasedTimeTable(){
        /**
         * Returns the list of rows (each row has 6 elements).
         */
        if (rowBasedTimeTable != null){
            return rowBasedTimeTable;
        }
        
        ArrayList<Time[]> rowBasedTimeTable = new ArrayList<Time[]>();
        int rowCount=0, size;

        // Find the maximum number of elements (that will be rowcount)
        for (ArrayList<Time> al : timetable){
            size = al.size();
            if (size > rowCount)
                rowCount = size;            
        }


        for (int i = 0; i < rowCount; i++ ){
            Time[] row = new Time[rowCount];
            //Weekday A
            try {
                row[0] = (timetable.get(0).get(i));
            }
            catch (Exception e){
                row[0] = null;
            }

            //Weekday B
            try {
                row[1] = (timetable.get(1).get(i));
            }
            catch (Exception e){
                row[1] = null;
            }

            //Saturday A
            try {
                row[2] = (timetable.get(2).get(i));
            }
            catch (Exception e){
                row[2] = null;
            }

            //Saturday B
            try {
                row[3] = (timetable.get(3).get(i));
            }
            catch (Exception e){
                row[3] = null;
            }

            //Sunday A
            try {
                row[4] = (timetable.get(4).get(i));
            }
            catch (Exception e){
                row[4] = null;
            }

            //Sunday B
            try {
                row[5] = (timetable.get(5).get(i));
            }
            catch (Exception e){
                row[5] = null;
            }

            rowBasedTimeTable.add(row);
        }

        

        return rowBasedTimeTable;

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
