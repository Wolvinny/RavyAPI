package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WhitelistEntry {
    private final JSONObject obj;
    public WhitelistEntry(JSONObject obj){
        this.obj = obj;
        try{
            obj.get("error");
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }catch (JSONException ign){}
    }

    public boolean getProvider(){
        return false;
    }

    /**
     * The reason why the user was whitelisted, usually 'STAFF'
     *
     */
    public String getReason(){
        return null;
    }
}
