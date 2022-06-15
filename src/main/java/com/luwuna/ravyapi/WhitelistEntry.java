package com.luwuna.ravyapi;

import kotlin.DeprecatedSinceKotlin;
import kotlin.ReplaceWith;

public class WhitelistEntry {

    private String reason;
    private String provider;

    public WhitelistEntry() {
    }

    public String getProvider() {
        return provider;
    }

    /**
     * The reason why the user was whitelisted, usually 'STAFF'
     * @return String;
     */

    public String getReason() {
        return reason;
    }
}
