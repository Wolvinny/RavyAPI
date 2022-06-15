package com.luwuna.ravyapi;

public class FullUserInfo {
    private String pronouns;
    private Trust trust;
    private WhitelistEntry[] whitelists;
    private BanEntry[] bans;
    private ReputationEntry[] rep;
    private SentinelEntry sentinel;

    /**
     * Gets all info about a user. Requires the user scope
     */
    public FullUserInfo() {
    }

    /**
     * Get the pronouns of the user
     * @return String
     */
    public String getPronouns() {
        return pronouns;
    }

    /**
     * Get the trust of the user
     * @return Trust
     */
    public Trust getTrust() {
        return trust;
    }


    public boolean isWhitelisted(){
        return whitelists.length >=1;
    }
    /**
     * Get the whitelists of the user. Check with isWhitelisted() first
     * @return WhitelistEntry[]
     */
    public WhitelistEntry[] getWhitelists() {
        return whitelists;
    }
    public boolean isBanned(){
        return bans.length >=1;
    }
    /**
     * Get the whitelists of the user. Check with isBanned() first
     * @return BanEntry[]
     */
    public BanEntry[] getBans() {
        return bans;
    }

    /**
     * Get the reputation of the user
     * @return ReputationEntry[]
     */
    public ReputationEntry[] getReputation() {
        return rep;
    }

    /**
     * Get the SentinelEntry of the user
     * @return SentinelEntry
     */
    public SentinelEntry getSentinel() {
        return sentinel;
    }
}
