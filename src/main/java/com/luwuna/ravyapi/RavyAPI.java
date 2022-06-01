package com.luwuna.ravyapi;

import com.luwuna.ravyapi.exceptions.InvalidTokenException;
import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RavyAPI{

    Request r;
    final OkHttpClient c = new OkHttpClient();
    String token;

    /**
     * Constructs the API and logs in with the token.
     *
     * @param token the token to login with
     * @throws InvalidTokenException if invalid token
     */
    public RavyAPI(@NotNull String token){
        this.token = token;
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
                   throw new InvalidTokenException("The token provided is invalid!");
           }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getGuild(@NotNull String id){

    }
    /**
     * Gets the scopes that the token has access to in a List<String>. All scopes can be found at <a href="https://docs.ravy.org/share/5bc92059-64ef-4d6d-816e-144b78e97d89">here</a>
     */
    public Token getToken(){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/tokens/@current")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
          res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            switch(res.code()){
                case 401:
                case 403:
                    throw new UnauthorizedRouteException("You don't have access to this route!");
                default:
                    return new Token(obj);
            }
        }catch (Exception ignored){
        }
        return null;
    }

    /**
     * Gets extended info about a user (bans, trust)
     * Pronouns are not included in this and can be fetched via .getPronouns()
     * @param id: the id of the user
     * @throws UnauthorizedRouteException if unauthorized
     */
    public ExtensiveUserInfo getInfo(@NotNull String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id)
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            switch(res.code()){
                case 401:
                case 403:
                    throw new UnauthorizedRouteException("You don't have access to this route!");
                default:
                    return new ExtensiveUserInfo(obj);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getGuildBans(){
        String reason;
        return null;
    }

    /**
     * Returns the user pronouns if set.
     * @param id: the id of the User
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
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            switch(res.code()){
                case 401:
                case 403:
                    throw new UnauthorizedRouteException("You don't have access to this route!");
                default:
                    return obj.get("pronouns").toString();
            }



        }catch (IOException ignored){
        }
        return null;
    }

    /**
     * Gets the whitelists of a user.
     * @param id: the id of the user
     * @return List<WhiteListEntry> if the user has whitelists, null if there are none
     *
     *
     */
    public List<WhitelistEntry> getWhitelists(@NotNull String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/whitelists")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            switch(res.code()){
                case 401:
                case 403:
                    throw new UnauthorizedRouteException("You don't have access to this route!");
            }
            List<WhitelistEntry> entry = new ArrayList<>();
            JSONArray arr = (JSONArray) obj.get("whitelists");
            if(arr.isEmpty()){
                return null;
            }
            arr.forEach(a -> entry.add(new WhitelistEntry((JSONObject) a)));
            return entry;
        }catch (IOException ignored){
        }
        return null;
    }
    public Trust getWhitelistsTrust(@NotNull String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/whitelists")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            switch(res.code()){
                case 401:
                case 403:
                    throw new UnauthorizedRouteException("You don't have access to this route!");
                default:
                    return new Trust((JSONObject) obj.get("trust"));
            }

        }catch (IOException ignored){
        }
        return null;
    }
    /**
     * Gets the trust based on Discordrep.com
     * @param id the userid
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
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            switch(res.code()){
                case 401:
                case 403:
                    throw new UnauthorizedRouteException("You don't have access to this route!");
                default:
                    return new ReputationEntry(obj);
            }

        }catch (Exception ignored){
        }
        return null;
    }
    public boolean isSentinelVerified(@NotNull String id){
        return false;
    }

}
