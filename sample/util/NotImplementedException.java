package util;

public class NotImplementedException extends RuntimeException{
    public NotImplementedException(String s){
        super(s);
    }
    public NotImplementedException(){
        this("This feature has not been implemented yet");
    }
}
