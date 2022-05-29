package com.luwuna.ravyapi;


import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExtensiveUserInfo {
   private JSONObject obj;
    private JSONObject trust;
    public ExtensiveUserInfo(JSONObject obj){
        this.obj = obj;
        try{
            obj.get("error");
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }catch (JSONException ign){}
        trust = (JSONObject) obj.get("trust");

    }

    public boolean isBanned(){
       JSONArray a = ((JSONArray) obj.get("bans"));
        return !a.isEmpty();
    }
    public String getPronouns(){
        try {
            String pronouns = obj.get("pronouns").toString();
            return pronouns;
        }catch (JSONException ign){
            throw new UnauthorizedRouteException("You don't have access to this route!");
        }
    }
    public int getTrustLevel(){
       return Integer.valueOf(trust.get("level").toString());
    }
    public String getTrustLabel(){
        return (String) trust.get("label");
    }

    /**
     * Checks whether the user has bans
     * @return null if the user is not banned, List<BanEntry> if the user is banned
     */
    public List<BanEntry> getBanEntries(){
        if(!isBanned()) return null;
        List<BanEntry> entry = new ArrayList<>();
        JSONArray arr = (JSONArray) obj.get("bans");
        arr.forEach(a ->{
            entry.add(new BanEntry((JSONObject) a ));
        });
        return entry;
    }

}
