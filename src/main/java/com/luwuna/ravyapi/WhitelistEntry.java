package com.luwuna.ravyapi;

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
     */
    public String getReason() {
        return reason;
    }
}
