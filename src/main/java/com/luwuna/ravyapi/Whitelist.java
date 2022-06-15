package com.luwuna.ravyapi;

public class Whitelist {
    private WhitelistEntry[] whitelists;
    private Trust trust;

    public Whitelist() {
    }

    public boolean isWhitelisted(){
        return whitelists.length >=1;
    }
    /**
     * Gets all the whitelists of an user. Check .isWhitelisted() first
     * @return boolean
     */
    public WhitelistEntry[] getWhitelists() {
        return whitelists;
    }

    /**
     * Get the trust based on the Whitelists
     * @return Trust
     */

    public Trust getTrust() {
        return trust;
    }
}
