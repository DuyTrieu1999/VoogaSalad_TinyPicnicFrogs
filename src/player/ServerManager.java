package player;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;

public class ServerManager {
    private static final String URL_LOCAL = "https://vooga-server.herokuapp.com";
    private static final String LOGIN_PATH="/login";
    private static final String REGISTER_PATH="/createuser";
    private static final String UPDATE_PATH="/updateProfile";
    private JSONParser parser;

    public ServerManager(){
        parser = new JSONParser();
    }

    public void testConnection() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(URL_LOCAL)).GET().version(HttpClient.Version.HTTP_1_1).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
    }

    public JSONObject login(String email, String password) throws IOException, InterruptedException {
        String body="{\n" +
                "\t\"user\":{\n" +
                "\t\t\"email\":\""+email+"\",\n" +
                "\t\t\"password\":\""+password+"\"\n" +
                "\t}\n" +
                "}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(URL_LOCAL+LOGIN_PATH)) .header("Content-Type", "application/json").PUT(HttpRequest.BodyPublisher.fromString(body)).version(HttpClient.Version.HTTP_1_1).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        try {
            return(JSONObject)parser.parse(response.body());
        } catch (ParseException e) {
            return null;
        }

    }

    public void register(String email, String password, String bio,String name ) throws IOException, InterruptedException {
        String body = "{\n" +
                "\t\"user\":{\n" +
                "\t\t\"email\":\""+email+"\",\n" +
                "\t\t\"password\":\""+password+"\",\n" +
                "\t\t\"bio\":\""+bio+"\",\n" +
                "\t\t\"name\":\""+name+"\"\n" +
                "\t}\n" +
                "}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(URL_LOCAL+REGISTER_PATH)) .header("Content-Type", "application/json").POST(HttpRequest.BodyPublisher.fromString(body)).version(HttpClient.Version.HTTP_1_1).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
    }
public JSONObject updateUser(String email, String password, String bio,String name ) throws IOException, InterruptedException {
    String body = "{\n" +
            "\t\"user\":{\n" +
            "\t\t\"email\":\""+email+"\",\n" +
            "\t\t\"password\":\""+password+"\",\n" +
            "\t\t\"bio\":\""+bio+"\",\n" +
            "\t\t\"name\":\""+name+"\"\n" +
            "\t}\n" +
            "}";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request=HttpRequest.newBuilder().uri(URI.create(URL_LOCAL+UPDATE_PATH)) .header("Content-Type", "application/json").PUT(HttpRequest.BodyPublisher.fromString(body)).version(HttpClient.Version.HTTP_1_1).build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
    try {
        return(JSONObject)parser.parse(response.body());
    } catch (ParseException e) {
        return null;
    }
}


}
