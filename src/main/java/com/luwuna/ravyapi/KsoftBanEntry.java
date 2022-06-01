package com.luwuna.ravyapi;

import org.json.JSONObject;

public class KsoftBanEntry {
    private final JSONObject obj;
    public KsoftBanEntry(JSONObject obj){
        this.obj = obj;
    }


    public boolean isBanned(){
        return obj.getBoolean("found");
    }
    public String getId(){
        return obj.getString("id");
    }
    public String getTag(){
        return obj.getString("tag");
    }
    public String getReason(){
        return obj.getString("reason");
    }
    public String getProof(){
        return obj.getString("proof");
    }
    public String getModerator(){
        return obj.getString("moderator");
    }
    public boolean isSevere(){
        return obj.getBoolean("severe");
    }
    public String getTimestamp(){
        return obj.getString("timestamp");
    }

}
