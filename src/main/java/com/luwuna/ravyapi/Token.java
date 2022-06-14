package com.luwuna.ravyapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luwuna.ravyapi.enums.TokenType;

public class Token {
    private String user;
    private String application;
    @JsonProperty("access")
    private String[] access;
    private String type;

    public Token() {
    }

    public String getUser() {
        return user;
    }

    public String getApplication() {
        return application;
    }

    public String[] getScopes() {
        return access;
    }

    public TokenType getType() {
        return TokenType.valueOf(type.toUpperCase());
    }
}
