package com.luwuna.ravyapi;


public class GuildInfo {
    private Trust trust;
    private BanEntry[] bans;

    public GuildInfo() {
    }

    public boolean isBanned() {
        return bans.length >= 1;
    }

    public Trust getTrust() {
        return trust;
    }


    /**
     * Checks whether the guild has bans
     *
     * @return null if the guild is not banned, List<BanEntry> if the guild is banned
     */
    public BanEntry[] getBans() {
        return bans;
    }

}
