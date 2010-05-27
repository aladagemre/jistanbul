package utils;

/**
 *
 * @author emre
 */
public class NoSuchLineException extends Exception {
    private String error;
    public NoSuchLineException(){
        super();
        error = "Unknown";
    }

    public NoSuchLineException(String err){
        super();
        error = err;
    }

    public String getError(){
        return error;
    }

}