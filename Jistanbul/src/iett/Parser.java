/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iett;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author emre
 */
public class Parser {
    private String from;
    private String to;
    private String code;
    private ArrayList<ArrayList<Time>> timetable;
    
    public Parser(){
        timetable = new ArrayList<ArrayList<Time>>();
    }
    public void flush(){
        from = null;
        to = null;
        timetable = new ArrayList<ArrayList<Time>>();
    }
    public Line getLine(){
        Line line = new Line(code);
        line.setTimetable(timetable);
        line.setFrom(from);
        line.setTo(to);
        return line;
    }
    public void parseTimes(String text, String direction){
        /**
         * Parses the given text and stores the times + from/to information.
         * Steps:
         *      Create a new empty timeList
         *      Split the text into time chunks (19:05)
         *      If the length of the array is 1, it means no buses,
         *          Add the empty timeList to timetable.
         *          Return
         *      Convert time array to ArrayList
         *      Remove and Parse first element to get stop name information.
         *      Remove and Parse last element, clear it and re-insert to list.
         *      Add the filled timeList to timetable.
         */
        int brIndex;
        String[] array;
        ArrayList<String> list;
        ArrayList<Time> timeList = new ArrayList<Time>();

        // Split the line to get the times.
        array = Pattern.compile("<br/>&nbsp;&nbsp;&nbsp;").split(text, 0);

        // If no bus on this day, add the empty list and return.
        if (array.length == 1){ //no bus for this day/way
            timetable.add(timeList);
            return;
        }
        
        // Convert to ArrayList
        list = new ArrayList(Arrays.asList(array));
        // Remove the first element (information)
        String firstline = list.remove(0);

        // Get the bus stop information.
        int start_index = firstline.indexOf("nde");
        int end_index = firstline.indexOf("dura");
        if (direction.equals("G"))
            from = firstline.substring(start_index + 4, end_index - 1);
        else
            to = firstline.substring(start_index + 4, end_index - 1);
        
        // Get the last item, we have the last time mixed with some other information :(
        String lastItem = list.remove(list.size()-1);
        // Get rid of what's there after (including) <br/>.
        brIndex = lastItem.indexOf("<br/>");
        // Place it back to list.
        list.add(lastItem.substring(0, brIndex));

        // Create time objects and add them to the list.
        for (String element : list){
            timeList.add(new Time(element));
        }

        // Add the timeList (for a day/stop) to our weekly timetable
        timetable.add(timeList);
        //return timeList;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
