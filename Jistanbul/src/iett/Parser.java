/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iett;

import utils.NoSuchLineException;
import utils.StringOperations;
import utils.TimeList;

/**
 *
 * @author emre
 */
public class Parser {
    private String from;
    private String to;
    private String code;
    private int numTimeLists;
    private TimeList[] timetable;

    public static void main(String[] args) {
        Parser p = new Parser();
        String[] result = StringOperations.split("&nbsp;&nbsp;&nbsp;122M Hattının<br />  &nbsp;&nbsp;&nbsp;is gününde S.SAHINBEY durağından <br />&nbsp;&nbsp;&nbsp;Kalkış Saatleri<br/>&nbsp;&nbsp;&nbsp;06:05<br/>&nbsp;&nbsp;&nbsp;06:20<br/>&nbsp;&nbsp;&nbsp;06:35<br/>&nbsp;&nbsp;&nbsp;06:50<br/>&nbsp;&nbsp;&nbsp;06:55<br/>&nbsp;&nbsp;&nbsp;07:05<br/>&nbsp;&nbsp;&nbsp;07:15<br/>&nbsp;&nbsp;&nbsp;07:30<br/>&nbsp;&nbsp;&nbsp;08:00<br/>&nbsp;&nbsp;&nbsp;14:45<br/>&nbsp;&nbsp;&nbsp;15:50<br/>&nbsp;&nbsp;&nbsp;16:30<br/>&nbsp;&nbsp;&nbsp;16:50<br/>&nbsp;&nbsp;&nbsp;17:10<br/>&nbsp;&nbsp;&nbsp;17:30<br/>&nbsp;&nbsp;&nbsp;18:20<br/>&nbsp;&nbsp;&nbsp;19:00<br/><br />&nbsp;&nbsp;&nbsp; <a href=\"index.php\">Ana Sayfa </a>", "<br/>&nbsp;&nbsp;&nbsp;");
        for (int i = 0; i < result.length; i++){
            System.out.println(result[i]);
        }
    }
    public Parser(){
        flush();
    }
    public void flush(){
        /**
         * Clears the information in Parser.
         */
        from = null;
        to = null;
        timetable = new TimeList[6];
        numTimeLists = 0;
    }
    public Line getLine(){
        /*
         * Creates a line object according to information parsed and returns it.
         */
        Line line = new Line(code);
        line.setTimetable(timetable);
        line.setFrom(from);
        line.setTo(to);
        return line;
    }


    public void parseTimes(String text, String direction) throws NoSuchLineException{
        /**
         * Parses the given text and stores the times + from/to information.
         * Steps:
         *      If the text is null, throws exception (no such line)
         *      Split the text into time chunks (19:05)
         *      Create a new empty timeList
         *      If the length of the array is 1, it means no buses,
         *          Add the empty timeList to timetable.
         *          Return
         *      Use first element to obtain from/to information.
         *      Clean up last element
         *      Use the resulting list[1:end] to fill the timeList with Time Objects.
         *      Add the filled timeList to timetable.
         */
        if (text == null){
            throw new NoSuchLineException("Line ("+ code + ") doesn't exist.");
        }

        int brIndex;
        String[] array;
        int lastIndex;
        // Split the line to get the times.
        array = StringOperations.split(text, "<br/>&nbsp;&nbsp;&nbsp;");
        for (lastIndex = 0; array[lastIndex]!=null; lastIndex++);
        TimeList timeList = new TimeList(lastIndex);

        // If no bus on this day, add the empty list and return.
        if (array.length == 1){ //no bus for this day/way
            timetable[numTimeLists++] = timeList;
            return;
        }

        // Get the first element (information)
        String firstline = array[0];

        // Get the bus stop information.
        int start_index = firstline.indexOf("nde");
        int end_index = firstline.indexOf("dura");
        if (direction.equals("G"))
            from = firstline.substring(start_index + 4, end_index - 1);
        else
            to = firstline.substring(start_index + 4, end_index - 1);

        // Get the last item, we have the last time mixed with some other information :(
        String lastItem = array[lastIndex-1];
        // Get rid of what's there after (including) <br/>.
        brIndex = lastItem.indexOf("<br/>");
        // Update last element
        array[lastIndex-1] = lastItem.substring(0, brIndex);

        // Create time objects and add them to the list.
        for (int i = 1; i < lastIndex; i++ ){
            timeList.add(new Time(array[i]));
        }

        // Add the timeList (for a day/stop) to our weekly timetable
        timetable[numTimeLists++] = timeList;
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
