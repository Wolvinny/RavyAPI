package com.luwuna.ravyapi;

import org.json.JSONArray;
import org.json.JSONObject;

public class BanEntry {
     private JSONObject obj;


    public BanEntry(JSONObject obj){
    this.obj = obj;
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
