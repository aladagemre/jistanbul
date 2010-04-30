package iett;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
/**
 *
 * @author emre
 */
public class Connector {
    public Connector(){
        //downloadLine(1, "G", "122M");
        downloadLine("122M");

    }
    public Line downloadLine(String code){
        /*
         * Downloads the line with the given code and returns the Line object.
         */
        
        Line line = new Line(code);
        ArrayList<ArrayList<Time>> timeTable = new ArrayList<ArrayList<Time>>();
        timeTable.add(downloadLine(1, "G", code));
        timeTable.add(downloadLine(1, "D", code));
        timeTable.add(downloadLine(2, "G", code));
        timeTable.add(downloadLine(2, "D", code));
        timeTable.add(downloadLine(3, "G", code));
        timeTable.add(downloadLine(3, "D", code));
        for (ArrayList<Time> table : timeTable){
            System.out.println(table);
        }

        return line;
    }

    public ArrayList<Time> downloadLine(int day, String direction, String code){

        // http://wap.iett.gov.tr/ht1.php?gun=1&bas=G&hk=122M&ARA=ARA
        // TODO: Check validity
        try {
            URL url = new URL(String.format("http://wap.iett.gov.tr/ht1.php?gun=%d&bas=%s&hk=%s&ARA=ARA", day, direction, code));
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            //wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String textLine;
            while ((textLine = rd.readLine()) != null) {
                if (textLine.startsWith("&nbsp;&nbsp;&nbsp;")){
                    return Parser.parseTimes(textLine);
                }
            }
            wr.close();
            rd.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public static void main(String[] args){
        Connector c = new Connector();
        
    }
}
