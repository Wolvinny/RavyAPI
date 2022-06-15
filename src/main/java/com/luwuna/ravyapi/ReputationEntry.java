package com.luwuna.ravyapi;

public class ReputationEntry {
    private String provider;
    private double score;
    private int upvotes;
    private int downvotes;

    public ReputationEntry() {
    }

    /**
     * Gets the provider for the entry
     * @return String
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Get the score
     * @return double
     */
    public double getScore() {
        return score;
    }

    /**
     * Get the amount of upvotes
     * @return int
     */
    public int getUpvotes() {
        return upvotes;
    }
    /**
     * Get the amount of upvotes
     * @return int
     */
    public int getDownvotes() {
        return downvotes;
    }
}
