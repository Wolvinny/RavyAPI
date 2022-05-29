package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RavyAPI{
    /**
     * Creates the basic API object.
     * @param RavyToken: the token to login with
     * @throws IllegalArgumentException if the token is not a Ravy token
     *
     */
    Request r;
    OkHttpClient c = new OkHttpClient();
    String token;
    public RavyAPI(@NotNull String RavyToken){
        token = RavyToken;
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/tokens/@current")
                .header("Authorization", token)
                .get().build();
        try {
           Response res =  c.newCall(r).execute();
           System.out.println(res.code());
           switch(res.code()){
               case 401:
               case 403:
                   throw new IllegalArgumentException("Invalid token!");
           }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Gets the scopes that the token has access to in a List<String>. All scopes can be found at https://docs.ravy.org/share/5bc92059-64ef-4d6d-816e-144b78e97d89
     */
    public List<String> getScopes(){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/tokens/@current")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
          res = c.newCall(r).execute();
          obj = new JSONObject(res.body().string());
        }catch (Exception e){
            res = null;
            obj = null;
        }
        JSONArray arr = (JSONArray) obj.get("access");
        List<String> scopes = new ArrayList<>();
        arr.forEach(a ->{
            scopes.add(a.toString());
        });
        return scopes;
    }

    /**
     * Gets the bans of a user. Returns a BanEntry object
     * Use .getBans()
     */
    public ExtensiveUserInfo getInfo(String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/bans")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            obj = new JSONObject(res.body().string());
            System.out.println(obj);
            return new ExtensiveUserInfo(obj);
        }catch (Exception e){
            e.printStackTrace();
            res = null;
            obj = null;
        }
        return null;
    }

    public String getGuildBans(){
        String reason;
        return null;
    }

    /**
     * Returns the user pronouns if set. Also accessible in ExtensiveUserInfo
     * @param id
     * @return pronouns if successful
     * @throws UnauthorizedRouteException if unauthorized
     */
    public String getPronouns(@NotNull String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/pronouns")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            obj = new JSONObject(res.body().string());
            try {
                return obj.get("pronouns").toString();
            }catch (JSONException e){
                throw new UnauthorizedRouteException("You don't have access to this route!");
            }
        }catch (IOException e){
            res = null;
            obj = null;
        }
        return null;
    }
    public String getTrust(@NotNull String id){
        return null;
    }
    public String  getWhiteListProvidercyb(@NotNull String id){
        return null;
    }
    public String  getWhiteListReason(@NotNull String id){

        return null;
    }

    /**
     * Gets the trust based on Discordrep.com
     * @param id
     * @return ReputationEntry
     * @throws UnauthorizedRouteException if the token has no access
     *
     */
    public ReputationEntry getReputation(@NotNull String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/pronouns")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            obj = new JSONObject(res.body().string());
                return new ReputationEntry(obj);
        }catch (Exception e){
            res = null;
            obj = null;
        }
        return null;
    }
    public boolean isSentinelVerified(@NotNull String id){
        return false;
    }

}
