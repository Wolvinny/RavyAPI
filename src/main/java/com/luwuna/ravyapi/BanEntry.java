package com.luwuna.ravyapi;

import com.luwuna.ravyapi.enums.ReasonKey;

public class BanEntry {
    private String provider;
    private String reasonKey;
    private String moderator;
    private String reason;

    /**
     * Gets info about the ban (check first with .isBanned())
     */
    public BanEntry() {
    }

    /**
     * Gets the reason why the user was banned
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Gets the ReasonKey why the user was banned
     * @return ReasonKey Enum
     */
    public ReasonKey getReasonKey() {
        return ReasonKey.valueOf(reasonKey);
    }

    /**
     * Gets the moderator who issued this ban
     * @return String moderator
     */
    public String getModerator() {
        return moderator;
    }

    /**
     * Gets the provider
     * @return String provider
     */
    public String getProvider() {
        return provider;
    }
}
