package authoring.authoring_backend;

public class GameData {
    private String title;
    private String description;
  private String path;
    private int mapWidth,mapHeight;
    public GameData(String titleP,String descriptionP, String filePath, int mapH,int mapW){
        title=titleP;
        description=descriptionP;
        path=filePath;
        mapHeight=mapH;
        mapWidth=mapW;
    }
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getPath(){return path;}
    public int getMapWidth(){return mapWidth;}
    public int getMapHeight(){return mapHeight;}
}
