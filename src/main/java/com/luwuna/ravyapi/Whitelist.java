package com.luwuna.ravyapi;

public class Whitelist {
    private WhitelistEntry[] whitelists;
    private Trust trust;

    public Whitelist() {
    }

    public WhitelistEntry[] getWhitelists() {
        return whitelists;
    }

    public Trust getTrust() {
        return trust;
    }
}
