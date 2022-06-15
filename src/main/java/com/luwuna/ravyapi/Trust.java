package com.luwuna.ravyapi;

public class Trust {
    int level;
    String label;

    public Trust() {

    }

    /**
     * Gets the trust level
     * @return int (0-6)
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the meaning of the number
     * @return String number
     */
    public String getLabel() {
        return label;
    }

}
