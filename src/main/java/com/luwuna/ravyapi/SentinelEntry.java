package com.luwuna.ravyapi;

public class SentinelEntry {
    private boolean verified;
    private String id;

    public SentinelEntry() {

    }
    /**
     * Whether the user is verified
     *
     * @return boolean
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * I honestly have no clue on why you'd need this, but I added it anyway
     *
     * @return String
     */
    public String getInternalDebugId() {
        return id;
    }
}
