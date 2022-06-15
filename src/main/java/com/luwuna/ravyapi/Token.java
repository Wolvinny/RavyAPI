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

    /**
     * The userId this token belongs to
     * @return String
     */
    public String getUser() {
        return user;
    }

    /**
     * The application id this token belongs to
     * @return String
     */
    public String getApplication() {
        return application;
    }

    /**
     * Get the scopes the token has access to. All scopes can be found <a href="https://docs.ravy.org/share/5bc92059-64ef-4d6d-816e-144b78e97d89">here</a>
     * @return String[]
     */
    public String[] getScopes() {
        return access;
    }

    /**
     * Get the TokenType
     * @return TokenType
     */
    public TokenType getType() {
        return TokenType.valueOf(type.toUpperCase());
    }
}
