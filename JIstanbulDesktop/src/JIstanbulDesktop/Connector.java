package JIstanbulDesktop;

import iett.Line;
import iett.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import utils.NoSuchLineException;

/**
 *
 * @author emre
 */
public class Connector {

    private Parser parser;

    public Connector() {
        parser = new Parser();
    }

    public Line downloadLine(String code) throws Exception {
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
        try {
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
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
    }

    public void downloadLine(int day, String direction, String code) throws NoSuchLineException, IOException {
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
        String data = encodeString("gun") + "=" + encodeString(Integer.toString(day));
        data += "&" + encodeString("bas") + "=" + encodeString(direction);
        data += "&" + encodeString("hk") + "=" + encodeString(code);
        data += "&" + encodeString("ARA") + "=" + encodeString("ARA");

        return data;
    }

    private String requestTimeLine(String urlString) throws IOException {
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
            if (textLine.startsWith("&nbsp;&nbsp;&nbsp;")) {
                return textLine;
            }
        }

        rd.close();
        return null;
    }

    private String encodeString(String input) {
        /**
         * Encodes given string in encoding ISO-8859-9
         */
        input = input.replace("ı", "%FD").replace("ö", "%F6").replace("ü", "%FC").replace("ş", "%FE").replace("ğ", "%F0").replace("ç", "%E7");
        input = input.replace("İ", "%DD").replace("Ö", "%D6").replace("Ü", "%DC").replace("Ş", "%DE").replace("Ğ", "%D0").replace("Ç", "%C7");
        return input;
    }

    public static void main(String[] args) {
        Connector c = new Connector();

    }
}
