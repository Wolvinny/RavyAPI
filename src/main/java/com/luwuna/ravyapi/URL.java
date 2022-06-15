package com.luwuna.ravyapi;

public class URL {
    private boolean isFraudulent;
    private String message;

    public URL() {
    }

    /**
     * Whether this url is fraudulent
     * @return boolean
     */
    public boolean isFraudulent() {
        return isFraudulent;
    }

    /**
     * Gets the reason why this url is fraudulent
     * @return String
     */
    public String getReason() {
        return message;
    }
}
