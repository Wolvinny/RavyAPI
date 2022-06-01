package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONException;
import org.json.JSONObject;

public class ReputationEntry {
    private final JSONObject obj;
    public ReputationEntry(JSONObject obj) {
        this.obj = obj;
        if(obj.has("error")) {
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }

    }
    public String getProvider(){
        return obj.getString("provider");
    }
    public double getScore(){
        return obj.getDouble("score");
    }
    public int getUpvotes(){
        return obj.getInt("upvotes");
    }
    public int getDownvotes(){
        return obj.getInt("downvotes");
    }
}
