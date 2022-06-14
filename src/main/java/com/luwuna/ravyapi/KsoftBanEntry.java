package com.luwuna.ravyapi;

public class KsoftBanEntry {

    private boolean found;
    private String id;
    private String tag;
    private String reason;
    private String proof;
    private String moderator;
    private boolean severe;
    private String timestamp;


    public KsoftBanEntry() {
    }


    public boolean isBanned() {
        return found;
    }

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getReason() {
        return reason;
    }

    public String getProof() {
        return proof;
    }

    public String getModerator() {
        return moderator;
    }

    public boolean isSevere() {
        return severe;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
