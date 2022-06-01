package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExtensiveGuildInfo {
    private final JSONObject obj;
    public ExtensiveGuildInfo(JSONObject obj){
        this.obj = obj;
        if(obj.has("error")) {
            throw new UnauthorizedRouteException("You dont have access to this route!");
        }

    }

    public boolean isBanned(){
        JSONArray a = ( obj.getJSONArray("bans"));
        return !a.isEmpty();
    }
    public Trust getTrust(){
        return new Trust((JSONObject) obj.get("trust"));
    }


    /**
     * Checks whether the guild has bans
     * @return null if the guild is not banned, List<BanEntry> if the guild is banned
     */
    public List<BanEntry> getBanEntries(){
        if(!isBanned()) return null;
        List<BanEntry> entry = new ArrayList<>();
        JSONArray arr = (JSONArray) obj.get("bans");
        arr.forEach(a -> entry.add(new BanEntry((JSONObject) a )));
        return entry;
    }

}
