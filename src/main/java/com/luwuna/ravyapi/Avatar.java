package com.luwuna.ravyapi;

import org.json.JSONObject;

public class Avatar {
    private final JSONObject obj;
    public Avatar(JSONObject obj){
        this.obj = obj;
    }

    /**
     * Whether the avatar is matched to a fraudulent avatar
     * @return true if matched
     */
    public boolean isMatched(){
        return obj.getBoolean("matched");
    }
    /**
     * Whether the avatar is matched to a fraudulent avatar
     * @return true if matched
     */
    public String getKey(){
        return obj.getString("key");
    }
    /**
     * How similar the avatar is to the matched avatar
     * @return 0.00-1.00
     */
    public double getSimilarity(){
        return obj.getDouble("similarity");
    }
}
