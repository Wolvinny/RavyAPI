package com.luwuna.ravyapi;

public class Avatar {
    private boolean matched;
    private String key;
    private double similarity;

    public Avatar() {
    }

    /**
     * Whether the avatar is matched to a fraudulent avatar
     *
     * @return true if matched
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * Whether the avatar is matched to a fraudulent avatar
     *
     * @return true if matched
     */
    public String getKey() {
        return key;
    }

    /**
     * How similar the avatar is to the matched avatar
     *
     * @return 0.00-1.00
     */
    public double getSimilarity() {
        return similarity;
    }
}
