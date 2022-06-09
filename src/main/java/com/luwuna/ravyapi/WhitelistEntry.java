package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;

import org.json.JSONObject;

public class WhitelistEntry {
    private final JSONObject obj;
    public WhitelistEntry(JSONObject obj){
        this.obj = obj;
        if(obj.has("error")) {
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }
    }

    public String getProvider(){
        return obj.getString("provider");
    }

    /**
     * The reason why the user was whitelisted, usually 'STAFF'
     *
     */
    public String getReason(){

        return obj.getString("reason");
    }
}
