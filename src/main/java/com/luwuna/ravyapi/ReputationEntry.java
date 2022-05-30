package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONException;
import org.json.JSONObject;

public class ReputationEntry {
    private final JSONObject obj;
    public ReputationEntry(JSONObject obj) {
        this.obj = obj;
        try{
            obj.get("error");
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }catch (JSONException ign){}

    }
    public String getProvider(){
        return (String) obj.get("provider");
    }
    public double getScore(){
        return Double.valueOf((String) obj.get("score"));
    }
    public int getUpvotes(){
        return Integer.valueOf((String) obj.get("upvotes"));
    }
    public int getDownvotes(){
        return Integer.valueOf((String) obj.get("downvotes"));
    }
}
