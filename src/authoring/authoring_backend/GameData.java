package authoring.authoring_backend;

public class GameData {
    private String title;
    private String description;
  private String path;
    private int mapWidth,mapHeight,squareWidth,squareHeight;
    protected GameData(String titleP,String descriptionP, String filePath, int mapH,int mapW,int squareW,int squareH){
        title=titleP;
        description=descriptionP;
        path=filePath;
        mapHeight=mapH;
        mapWidth=mapW;
        squareHeight=squareH;
        squareWidth=squareW;
    }
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getPath(){return path;}
    public int getMapWidth(){return mapWidth;}
    public int getMapHeight(){return mapHeight;}
    public int getSquareWidth(){return squareWidth;}
    public int getSquareHeight(){return squareHeight;}
}
