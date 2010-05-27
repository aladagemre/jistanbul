/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author emre
 */
public class StringOperations {

        public static String[] split(String text, String delimiter){
        /**
         * Splits the given string according to the delimiter given.
         * Should be improved. Not working properly.
         */
        int startIndex, delimLength;
        delimLength = delimiter.length();
        String[] result = new String[text.length() / delimLength];

        int numElements=0;
        String temp;

        while (!text.equals("")){
            startIndex = text.indexOf(delimiter);
            if (startIndex == -1){
                result[numElements++] = text;
                break;
            }
            temp = text.substring(0, startIndex);
            result[numElements++] = temp;
            text = text.substring(startIndex + delimLength);
        }
        String[] output = new String[numElements+1];

            for (int i = 0; i<numElements; i++){
                output[i] = result[i];
            }
        return output;

    }
}
