package com.softpian.restfulapiclient.event;

public class ResponseErrorEvent {

    private int mResponseCode;

    public ResponseErrorEvent(int responseCode) {
        mResponseCode = responseCode;
    }

    public int getResponseCode() {
        return mResponseCode;
    }
}
