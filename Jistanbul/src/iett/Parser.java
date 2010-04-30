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
    public Parser(){
        
    }
    public static ArrayList<Time> parseTimes(String text){
        int brIndex;
        String[] array;
        ArrayList<String> list;
        ArrayList<Time> timeList = new ArrayList<Time>();

        // Split the line to get the times.
        array = Pattern.compile("<br/>&nbsp;&nbsp;&nbsp;").split(text, 0);
        if (array.length == 1) //no bus for this day/way
            return null;
        // Convert to ArrayList
        list = new ArrayList(Arrays.asList(array));
        // Remove the first element (information)
        list.remove(0);
        // Get the last item
        String lastItem = list.remove(list.size()-1);
        // Get rid of what's there after (including) <br/>.
        brIndex = lastItem.indexOf("<br/>");
        // Place it back to list.
        list.add(lastItem.substring(0, brIndex));
        
        for (String element : list){
            timeList.add(new Time(element));
        }
        
        return timeList;
    }

}
