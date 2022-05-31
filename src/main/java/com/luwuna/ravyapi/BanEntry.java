package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BanEntry {
     private JSONObject obj;

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
        return (String) obj.get("reason");
    }
    public String getReasonKey(){
        return (String) obj.get("reasonKey");
    }
    public String getModerator(){
        return (String) obj.get("moderator");
    }
    public String getProvider(){
        return (String) obj.get("provider");
    }
}
