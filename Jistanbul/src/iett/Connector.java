package iett;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author emre
 */
public class Connector {
    private Parser parser;
    
    public Connector(){
        parser = new Parser();
    }
    
    public Line downloadLine(String code) throws Exception{
        /*
         * Downloads the line with the given code and returns the Line object.
         * How does it do that:
         *  Sets the code for the parser
         *  Downloads all timelists and gives them to parser.
         *  Parser saves these timeLists.
         *  We request the parser to create a Line object and give it to us.
         *  We flush (clear) the parser information
         *  We return the Line object.
         */
        parser.setCode(code);
        
        downloadLine(1, "G", code);
        downloadLine(1, "D", code);
        downloadLine(2, "G", code);
        downloadLine(2, "D", code);
        downloadLine(3, "G", code);
        downloadLine(3, "D", code);
        
        Line line = parser.getLine();
        parser.flush();
        
        return line;
        
    }


    public void downloadLine(int day, String direction, String code) throws Exception{
        /*
         * Downloads a specific line/day/direction.
         * Performs a GET request to the IETT Wap page.
         * Sends the string information to parser.
         * 
         */
        // http://wap.iett.gov.tr/ht1.php?gun=1&bas=G&hk=122M&ARA=ARA
        // TODO: Check validity
        String data = prepareGetData(day, direction, code);
        String timeLine = requestTimeLine(String.format("http://wap.iett.gov.tr/ht1.php?%s", data));
        parser.parseTimes(timeLine, direction);

    }

    private String prepareGetData(int day, String direction, String code) throws UnsupportedEncodingException {
        /**
         * Prepares URL postfix for the request parameters.
         */
        String data = URLEncoder.encode("gun", "ISO-8859-9") + "=" + URLEncoder.encode(Integer.toString(day), "ISO-8859-9");
        data += "&" + URLEncoder.encode("bas", "ISO-8859-9") + "=" + URLEncoder.encode(direction, "ISO-8859-9");
        data += "&" + URLEncoder.encode("hk", "ISO-8859-9") + "=" + URLEncoder.encode(code, "ISO-8859-9");
        data += "&" + URLEncoder.encode("ARA", "ISO-8859-9") + "=" + URLEncoder.encode("ARA", "ISO-8859-9");

        return data;
    }

    private String requestTimeLine(String urlString) throws Exception {
        /**
         * Downloads the given URL and returns the line with the times.
         */
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String textLine;
        while ((textLine = rd.readLine()) != null) {
            if (textLine.startsWith("&nbsp;&nbsp;&nbsp;")){
                return textLine;
            }
        }

        rd.close();
        return null;
    }

    public static void main(String[] args){
        Connector c = new Connector();
        
    }
}
