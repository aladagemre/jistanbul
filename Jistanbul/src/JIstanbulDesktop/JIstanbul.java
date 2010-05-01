/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JIstanbulDesktop;

import iett.Connector;
import java.util.Scanner;

/**
 *
 * @author emre
 */
public class JIstanbul {
    private Connector conn;

    public JIstanbul() {
        String code;
        Scanner sc = new Scanner(System.in);
        conn = new Connector();
        
        while (true){
            System.out.println("Enter the line code:");
            code = sc.next();
            conn.downloadLine(code);
        }
    }
    public static void main(String[] args) {
        JIstanbul j = new JIstanbul();
    }

}
