/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;
import iett.Time;

/**
 *
 * @author emre
 */
public class TimeList {
    private Time[] elements;
    private int numElements;
    private int maxSize;
    
    public TimeList(){
        elements = new Time[100];
        numElements = 0;
        maxSize = 100;
    }
    public TimeList(int size){
        elements = new Time[size];
        numElements = 0;
        maxSize = size;
    }
    public TimeList(Time[] array){
        elements = array;
        int i = 0;
        while (i < elements.length && elements[i] != null){
            i++;
        }
        numElements = i;
        //maxSize = (int) (numElements * 1.5);
        maxSize = array.length;
    }

    public Time[] toArray(){
        return elements;
    }
    public int size(){
        return numElements;
    }
    public void add(Time element){
        if (numElements < maxSize){
            elements[numElements++] = element;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }
    public Time remove(int index){
        int i;
        Time element = elements[index];
        for (i = index + 1; i < numElements; i++){
            elements[i-1] = elements[i];
        }
        elements[i] = null;
        numElements--;
        return element;
    }

    public Time get(int index){
        if (index < numElements){
            return elements[index];
        }
        else{
            throw new IndexOutOfBoundsException();
        }
            
    }

}
