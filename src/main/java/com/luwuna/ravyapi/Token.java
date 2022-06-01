package com.luwuna.ravyapi;

import com.luwuna.ravyapi.enums.TokenType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Token {
    private final JSONObject obj;
    public Token(JSONObject obj){
        this.obj = obj;
    }
    public List<String> getScopes(){
        JSONArray arr = (JSONArray) obj.get("access");
        List<String> scopes = new ArrayList<>();
        arr.forEach(a -> scopes.add(a.toString()));
        return scopes;
    }
    public TokenType getType(){
        switch (obj.get("type").toString()){
            case "ravy":
                return TokenType.RAVY;
            case "ksoft":
                return TokenType.KSOFT;
        }
        return TokenType.UNKNOWN;
    }
}
