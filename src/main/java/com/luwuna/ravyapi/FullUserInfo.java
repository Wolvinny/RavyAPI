package com.luwuna.ravyapi;

public class FullUserInfo {
    private String pronouns;
    private Trust trust;
    private WhitelistEntry[] whitelists;
    private BanEntry[] bans;
    private ReputationEntry[] rep;
    private SentinelEntry sentinel;


    public FullUserInfo() {
    }

    public String getPronouns() {
        return pronouns;
    }

    public Trust getTrust() {
        return trust;
    }

    public WhitelistEntry[] getWhitelists() {
        return whitelists;
    }

    public BanEntry[] getBans() {
        return bans;
    }

    public ReputationEntry[] getReputation() {
        return rep;
    }

    public SentinelEntry getSentinel() {
        return sentinel;
    }
}
