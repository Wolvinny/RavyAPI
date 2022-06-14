package com.luwuna.ravyapi;

public class ReputationEntry {
    private String provider;
    private double score;
    private int upvotes;
    private int downvotes;

    public ReputationEntry() {
    }

    public String getProvider() {
        return provider;
    }

    public double getScore() {
        return score;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }
}
