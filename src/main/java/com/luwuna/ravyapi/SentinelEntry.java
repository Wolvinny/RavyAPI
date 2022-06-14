package com.luwuna.ravyapi;

public class SentinelEntry {
    private boolean verified;
    private String id;

    public SentinelEntry() {

    }

    public boolean isVerified() {
        return verified;
    }

    /**
     * I honestly have no clue on why you'd need this, but I added it anyway
     *
     * @return the debug id
     */
    public String getInternalDebugId() {
        return id;
    }
}
