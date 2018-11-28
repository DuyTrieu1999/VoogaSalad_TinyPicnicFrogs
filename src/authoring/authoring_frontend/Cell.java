package authoring.authoring_frontend;

import java.util.ArrayList;

public class Cell {
    private int x;
    private int y;
    private ArrayList<Actor> myActors;

    Cell(int myX, int myY){
        x = myX;
        y = myY;
        myActors = new ArrayList<>();
    }

    public void addActor(Actor toAdd){
        myActors.add(toAdd);
    }

    public void removeActor(){
        myActors.remove(myActors.size()-1);
    }

    public ArrayList<Actor> getActors(){
        return myActors;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
