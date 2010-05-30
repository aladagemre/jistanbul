import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

import java.util.Calendar;


import iett.Parser;
import javax.microedition.lcdui.List;
import org.netbeans.microedition.lcdui.TableItem;
import utils.NoSuchLineException;
import utils.TimeList;

public class JIstanbulMobileMIDlet extends MIDlet implements CommandListener {

    private Command exitCommand = new Command("Exit", Command.EXIT, 1);
    private Command sendCommand = new Command("Send", Command.OK, 1);
    private Command backCommand = new Command("Back", Command.OK, 1);
    private Display display;
    private String defaultCode = "122M";
    private Form mainForm, resultForm;
    private TextField lineCode = new TextField(null, null, 5, TextField.ANY);
    private TableItem tableItem;
    private List resultList;
    private StringItem resultItem;
    private String result, URLString;
    private Form mWaitForm;

    public JIstanbulMobileMIDlet() {
        display = Display.getDisplay(this);
    }

    public void startApp() {
        mainForm = new Form("Hat bilgileri");
        mainForm.append(lineCode);
        mainForm.addCommand(sendCommand);
        mainForm.addCommand(exitCommand);
        mainForm.setCommandListener(this);
        display.setCurrent(mainForm);
        mWaitForm = new Form("Lutfen Bekleyiniz...");
        lineCode.setString("122M");
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable s) {
        if (c == sendCommand) {
            result = "Fetching...";
            resultItem = new StringItem(null, result);
            resultForm = new Form("Result");
            //tableItem = new TableItem(display, "tableItem");
            resultList = new List("Times", 1);

            URLString = "hk=" + lineCode.getString();

            display.setCurrent(mWaitForm);
            fetchTimes(true);
            fetchTimes(false);


            //resultItem.setText(result);

            resultForm.addCommand(backCommand);
            resultForm.setCommandListener(this);

            //resultList.addCommand(backCommand);
            //resultList.setCommandListener(this);
        } else if (c == backCommand) {
            lineCode.setString(defaultCode);
            display.setCurrent(mainForm);
        } else {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    private void fetchTimes(final boolean forward) {
        Thread t = new Thread(new Runnable() {

            public Parser p;
            public TimeList t;
            public boolean fw = forward;

            private String getLine(int day, String direction, String code) throws IOException {
                String content = "";
                try {
                    InputStream is = null;
                    String url = "http://wap.iett.gov.tr/ht1.php?gun=" + day + "&bas=" + direction + "&hk=" + code + "&ARA=ARA";
                    HttpConnection httpConn = (HttpConnection) Connector.open(url);

                    // Setup HTTP Request
                    httpConn.setRequestMethod(HttpConnection.GET);
                    httpConn.setRequestProperty("User-Agent",
                            "Profile/MIDP-2.0 Confirguration/CLDC-1.0");
                    /** Initiate connection and check for the response code. If the
                    response code is HTTP_OK then get the content from the target
                     **/
                    int respCode = httpConn.getResponseCode();
                    if (respCode == httpConn.HTTP_OK) {
                        StringBuffer sb = new StringBuffer();
                        is = httpConn.openDataInputStream();
                        int chr;
                        while ((chr = is.read()) != -1) {
                            sb.append((char) chr);
                        }
                        content = sb.toString();
                        System.out.println(content);
                    } else {
                        System.out.println("Connection failed.");
                    }

                } catch (IOException e2) {
                }
                return content;
            }

            public void run() {
                int day;

                try {
                    p = new Parser();
                    Calendar cal = Calendar.getInstance();
                    switch (cal.get(Calendar.DAY_OF_WEEK)) {
                        case 1:
                            //sunday
                            day = 3;
                            break;
                        case 6:
                            //saturday
                            day = 2;
                            break;
                        default:
                            //weekday
                            day = 1;
                    }
                    System.out.println("Choose day");
                    try {
                        String direction;
                        if (fw) {
                            direction = "G";
                        } else {
                            direction = "D";
                        }
                        String lineList = getLine(day, direction, lineCode.getString());
                        p.parseTimes(lineList, direction);
                        t = p.getLine().getTimetable()[0];
                        if (fw) {
                            resultForm.append("Kalkis: " + p.getLine().getFrom() + "\n");
                        } else {
                            resultForm.append("Kalkis: " + p.getLine().getTo() + "\n");
                        }
                        for (int i = 0; i < t.size(); i++) {
                            resultForm.append(t.get(i).toString());

                        }
                        resultForm.append("\n\n");
                        display.setCurrent(resultForm);

                    } catch (NoSuchLineException e) {
                        System.out.println("No such line");
                        resultList.setTitle("No such line");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        t.start();
    }
}
