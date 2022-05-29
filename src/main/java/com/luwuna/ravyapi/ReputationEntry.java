package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONException;
import org.json.JSONObject;

public class ReputationEntry {
    private JSONObject obj;
    public ReputationEntry(JSONObject obj) {
        this.obj = obj;
        System.out.println(obj);
        try{
            obj.get("error");
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }catch (JSONException ign){}

    }
    public String getProvider(){
        if(obj == null) return null;
        return (String) obj.get("provider");
    }
    public double getScore(){
        if(obj == null) return 0;
        return Double.valueOf((String) obj.get("score"));
    }
    public int getUpvotes(){
        if(obj == null) return 0;
        return Integer.valueOf((String) obj.get("upvotes"));
    }
    public int getDownvotes(){
        if(obj == null) return 0;
        return Integer.valueOf((String) obj.get("downvotes"));
    }
}
