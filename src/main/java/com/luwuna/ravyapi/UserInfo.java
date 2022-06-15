package com.luwuna.ravyapi;


public class UserInfo {
    private BanEntry[] bans;
    private Trust trust;

    public UserInfo() {
    }

    /**
     * Whether the user is banned. This should always be checked before trying to get the BanEntries to avoid errors
     * @return boolean
     */
    public boolean isBanned() {
        return bans.length >= 1;
    }
    /**
     * Returns the trust based purely on the BanEntry (no other factors influence this score)
     * @return boolean
     */
    public Trust getTrust() {
        return trust;
    }

    /**
     * Gets the bans of the user. Make sure to check with .isBanned() first to avoid exceptions
     * @return BanEntry[]
     */
    public BanEntry[] getBans() {
        return bans;
    }

}
