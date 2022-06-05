package com.luwuna.ravyapi;

import org.json.JSONObject;

public class URL {
    private final JSONObject obj;
    public URL(JSONObject obj){
        this.obj = obj;
    }
    public boolean isFraudulent(){
        return obj.getBoolean("isFraudulent");
    }
    public String getMessage(){
        return obj.getString("message");
    }
}
