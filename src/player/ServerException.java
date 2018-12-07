package player;

public class ServerException extends Exception {
    Exception myException;
    ServerException(Exception e){
        myException = e;
    }

    public Exception getException(){
        return myException;
    }
}
