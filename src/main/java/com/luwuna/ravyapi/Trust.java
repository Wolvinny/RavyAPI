package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONObject;

public class Trust {
    private final JSONObject obj;
    public Trust(JSONObject obj){
        this.obj = obj;
        if(obj.has("error")) {
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }
    }

    /**
     * Returns a number from 1-6. Default (no data) is 3.
     * @return int level
     */
    public int getLevel(){
        return Integer.parseInt(obj.get("level").toString());
    }
    /**
     * Returns the meaning of the number
     * @return String number
     */
    public String getLabel(){
        return obj.get("label").toString();
    }

}
