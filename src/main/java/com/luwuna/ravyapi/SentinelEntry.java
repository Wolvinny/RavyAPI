package com.luwuna.ravyapi;

import org.json.JSONObject;

public class SentinelEntry {
    private final JSONObject obj;
    public SentinelEntry(JSONObject obj){
        this.obj = obj;
    }
    public boolean isVerified(){
        return obj.getBoolean("verified");
    }
    /**
     * I honestly have no clue on why you'd need this, but i added it anyways
     * @return the debug id
     */
    public String getInternalDebugId(){
        return obj.getString("id");
    }
}
