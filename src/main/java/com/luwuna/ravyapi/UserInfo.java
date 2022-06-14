package com.luwuna.ravyapi;


public class UserInfo {
    private BanEntry[] bans;
    private Trust trust;

    public UserInfo() {
    }

    public boolean isBanned() {
        return bans.length >= 1;
    }

    public Trust getTrust() {
        return trust;
    }

    public BanEntry[] getBans() {
        return bans;
    }

}
