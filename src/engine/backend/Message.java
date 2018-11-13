package engine.backend;

public class Message {
    String myMessageString;
    Message(String messageString){
        myMessageString = messageString;
    }
    public String getMessageString(){
        return myMessageString;
    }
}
