package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BanEntry {
     private final JSONObject obj;

    /**
     *
     * @throws NullPointerException if not banned
     */
    public BanEntry(JSONObject obj){
    this.obj = obj;
    if(obj.has("error")) {
        throw new UnauthorizedRouteException("You dont have access to this route!");
    }

    }
    public String getReason(){
        return obj.getString("reason");
    }
    public String getReasonKey(){
        return obj.getString("reasonKey");
    }
    public String getModerator(){
        return obj.getString("moderator");
    }
    public String getProvider(){
        return obj.getString("provider");
    }
}
