package com.jet.Stepping;

public interface Callback {
    /**
     * Takes a result and process it
     */
    void calculated(int result);

    /**
     * Takes an error message
     */
    void failed(String errorMsg);
}
