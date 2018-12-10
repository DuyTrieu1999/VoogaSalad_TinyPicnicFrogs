package authoring.authoring_backend;

import engine.backend.DialogueTreeNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DialogManager {
    private Map<String, DialogueTreeNode>dialogMap;
    public DialogManager(){
        dialogMap=new HashMap<>();
    }
    public DialogueTreeNode createDialog(String dialogName,JSONArray dialogNodes, JSONObject dialogTree){
        Map<String,DialogueTreeNode>nodeMap= new HashMap<>();
        for(int i=0;i<dialogNodes.size();i+=1){
            JSONObject obj= (JSONObject)dialogNodes.get(i);
            nodeMap.put((String)obj.get("key"),new DialogueTreeNode((String)obj.get("value")));
        }

        dfs(nodeMap,nodeMap.get("root"),new HashSet<>(),dialogTree,"root");
        dialogMap.put(dialogName,nodeMap.get("root"));
        return nodeMap.get("root");
    }
    private void dfs(Map<String,DialogueTreeNode>nodes,DialogueTreeNode current, Set<DialogueTreeNode>visited, JSONObject dialogTree,String leaf){
        JSONArray arr=(JSONArray) dialogTree.get(leaf);
        if(arr.size()==0||arr==null){return;}
        for(int i=0;i<arr.size();i+=1){
            JSONObject entry= (JSONObject)arr.get(i);
            current.putChild((String)entry.get("prompt"),nodes.get((String)entry.get("node")));
            if(!visited.contains(nodes.get((String)entry.get("node")))){
                visited.add(nodes.get((String)entry.get("node")));
                dfs(nodes,nodes.get((String)entry.get("node")),visited,dialogTree,(String)entry.get("node"));}
        }
    }

    public DialogueTreeNode getDialog(String key){
        return dialogMap.get(key);
    }
}
