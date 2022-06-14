package com.luwuna.ravyapi;

import com.luwuna.ravyapi.enums.ReasonKey;

public class BanEntry {
    private String provider;
    private String reasonKey;
    private String moderator;
    private String reason;

    public BanEntry() {
    }

    public String getReason() {
        return reason;
    }

    public ReasonKey getReasonKey() {
        return ReasonKey.valueOf(reasonKey);
    }

    public String getModerator() {
        return moderator;
    }

    public String getProvider() {
        return provider;
    }
}
