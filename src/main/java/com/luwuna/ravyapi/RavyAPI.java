package com.luwuna.ravyapi;

import com.luwuna.ravyapi.enums.Method;
import com.luwuna.ravyapi.exceptions.InvalidTokenException;
import com.luwuna.ravyapi.exceptions.UnauthorizedRouteException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RavyAPI{
    private boolean isValid;
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
            if(!hasAccess(res.code()))  {
                isValid = false;
                throw new InvalidTokenException("The token provided is invalid!");
            }
            isValid = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Gets the scopes that the token has access to in a List<String>. All scopes can be found at <a href="https://docs.ravy.org/share/5bc92059-64ef-4d6d-816e-144b78e97d89">here</a>
     */
    public Token getToken(){
        if(!isValid) return null;
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
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new Token(obj);
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
        if(!isValid) return null;
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/bans")
                .header("Authorization", token)

                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new ExtensiveUserInfo(obj);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the user pronouns if set.
     * @param id: the id of the User
     * @return pronouns if successful
     * @throws UnauthorizedRouteException if unauthorized
     */
    public String getPronouns(@NotNull String id){
        if(!isValid) return null;
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
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return obj.getString("pronouns");



        }catch (IOException ignored){
        }
        return null;
    }

    public ExtensiveGuildInfo getGuildInfo(String id){
        if(!isValid) return null;
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/guilds/" + id)
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new ExtensiveGuildInfo(obj);
        }catch (IOException ignored){
        }
        return null;
    }

    /**
     * Checks if a url is fraudulent
     * @param url: The url to check
     * @return URL object
     */
    public URL getUrl(String url){
        if(!isValid) return null;
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/?url=" + url)
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new URL(obj);
        }catch (IOException ignored){
        }
        return null;
    }

    /**
     * Checks if a url is fraudulent
     * @param url: The url to check
     * @param phishermanToken: Optional: a phisherman.gg token if you have one
     * @param phisermanUserId: Optional, required if passing a phrisermanToken The id of the user the token is connected to
     * @return URL object
     */
    public URL getUrl(String url, String phishermanToken, String phisermanUserId){
        if(!isValid) return null;
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/?url=" + url + "&phisherman_token="+ phishermanToken + "&phiserman_user=" +phisermanUserId)
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new URL(obj);
        }catch (IOException ignored){
        }
        return null;
    }

    /**
     * Checks an avatar to see if its fraudulent.
     * @param link: The link to the avatar, starting with https://cdn.discord.com"
     * @param threshold: How similar the avatar need to be to match (0.00-1.00), default 0.97
     * @param method The method used to match the avatars: ssim, phash or default (phash)
     * @return The Avatar object
     */
    public Avatar getAvatar(String link, Double threshold, Method method){
        if(!isValid) return null;
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/?avatar=" + link + "&threshold="+threshold + "&method=" + method.name().toLowerCase())
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new Avatar(obj);
        }catch (IOException ignored){
        }
        return null;
    }
    public Avatar getAvatar(File avatarFile, Double threshold, Method method){
        if(!isValid) return null;
        RequestBody file = RequestBody.create(new File(""), MediaType.parse("application/octet-stream; charset=utf-8"));
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .addHeader("Content-type:", "application/octet-stream")
                .url("https://ravy.org/api/v1/avatars/?threshold="+threshold + "&method=" + method.name().toLowerCase())
                .post(file)
                .build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            assert res.body() != null;
            obj = new JSONObject(res.body().string());
            if (!hasAccess(res.code())) throw new UnauthorizedRouteException("You don't have access to this route!");
            return new Avatar(obj);
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
        if(!isValid) return null;
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
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
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
        if(!isValid) return null;
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
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new Trust(obj);

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
        if(!isValid) return null;
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
            if(!hasAccess(res.code()))  throw new UnauthorizedRouteException("You don't have access to this route!");
            return new ReputationEntry(obj);

        }catch (Exception ignored){
        }
        return null;
    }
    public boolean isSentinelVerified(@NotNull String id){

        return false;
    }


    private boolean hasAccess(int code){
        switch(code){
            case 401:
            case 403:
               return false;
            default:
                return true;
        }
    }
}
