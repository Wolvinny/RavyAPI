package com.luwuna.ravyapi;

import org.json.JSONObject;

public class Trust {
    private JSONObject obj;
    public Trust(JSONObject obj){
        this.obj = obj;
    }

    /**
     * Returns a number from 1-6. Default (no data) is 3.
     * @return int level
     */
    public int getLevel(){
        return Integer.valueOf(obj.get("level").toString());
    }
    /**
     * Returns the meaning of the number
     * @return String number
     */
    public String getLabel(){
        return obj.get("label").toString();
    }

}
